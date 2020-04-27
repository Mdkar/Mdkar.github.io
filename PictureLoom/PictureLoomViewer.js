class Point{
	constructor(x, y) {
		this.x = x;
		this.y = y;
	}
	getX() {
		return this.x;
	}
	getY() {
		return this.y;
	}
}
var scale = 0.8;
var currFrame = 0;
var inExternalFrame = false;
var canvas = document.getElementById('canvas1');
var div = document.getElementById('div1');
var div2 = document.getElementById('div2');
var div3 = document.getElementById('div3');
var play = document.getElementById('play');
var slider = document.getElementById('slider');
var file = document.getElementById('file1');
var speedDisp = document.getElementById('speed');
var loop = document.getElementById('loop');
var colorBox = document.getElementById('color');
canvas.width = Math.min(window.innerWidth*scale, window.innerHeight*scale);
canvas.height = Math.min(window.innerWidth*scale, window.innerHeight*scale);
div.style.width = canvas.width + "px";
slider.style.width = canvas.width - play.offsetWidth - 5 + "px";
var ctx = canvas.getContext('2d');
var nPegs;
var minGap;
var maxMoves;
var lineWeight;
var pegs;
var moves;
var currMove;
var isPlay = false;
var speed = 1;
var animationFunc;
var color = "000000";
var alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+-={}|[];:?./"; //base87
//WARNING: This uses reserved/unsafe characters to squeeze more data into a smaller URL, this is non-standard
function inIframe () {
	try {
		return window.self !== window.top;
	} catch (e) {
		return true;
	}
}
animationFunc = function () {
    return currMove+speed;
};
if(!inIframe()){
	div2.hidden = false;
	div3.hidden = false;
	document.addEventListener('keydown', logKeydown);
    file.addEventListener('change', handleFileSelect, false);
    colorBox.addEventListener('change', function (event) {
    	if(colorBox.value.length == 6){
    		color = colorBox.value;
    		ctx.clearRect(0,0,canvas.width,canvas.height);
    		drawLoom();
    	}
    });
    loop.addEventListener('change', function (event) {
        if (loop.checked) {
            animationFunc = function () {
            	var c = (maxMoves/2)*(1+Math.cos(speed*currFrame/20))
            	if(c == maxMoves){
            		currFrame = 0;
            	}
            	currFrame++; 
                return c;
            };
        } else {
            animationFunc = function () {
                return currMove+speed;
            };
        }
    });
} else {
	if(document.referrer.indexOf("dkar") == -1){
	    inExternalFrame = true;
	    document.getElementById("canvasLink").href="https://mdkar.github.io/";
	}
	var divSlider = document.getElementById("divSlider");
	window.addEventListener('message', function(event) {
	    if (typeof event.data == 'object' && event.data.call=='sendValue') {
		    handleIframeData(event.data.value);// Do something with event.data.value;
	    }
	}, false);
}

function handleIframeData(data){
    var dataArr = data.split("#");
    if(dataArr[0] == "params"){
    	divSlider.hidden = (dataArr[1] == "false");
        if(divSlider.hidden){
    	    document.getElementsByTagName("body")[0].style.margin = 0;
    	    canvas1.style.border = "none";
    	    scale = 1;
    	    resizeCanvas();
        }
        speed = parseFloat(dataArr[2]);
        animationFunc = new Function('return ' + dataArr[3])()
        color = dataArr[4];
        if(divSlider.hidden){
        	togglePlay();
        	window.setTimeout(function(){if(!isPlay){togglePlay();}},100);
        }
    }
}

slider.addEventListener('input', handleSlider, false);
var reader = new FileReader();

function handleSlider(evt){
	if(isPlay){
		togglePlay();
	}
	currMove = parseInt(slider.value);
	ctx.clearRect(0,0,canvas.width,canvas.height);
	drawLoom();
}

function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object
    // files is a FileList of File objects. List some properties.
    var output = [];
    var f = files[0];
    if(f.type == 'text/plain'){
        reader.readAsText(f);
        reader.onload = function(e) {
            var fileSplits = reader.result.split('\n');
            var queryStr = "?data=";
            for(var i = 0; i < fileSplits.length; i++){
                queryStr += toBase87(parseInt(fileSplits[i]));
                queryStr += ',';
            }
            queryStr = queryStr.substring(0,queryStr.length-2);
            var baseUrlEnd = window.location.href.indexOf("?data=");
            if(baseUrlEnd > -1){
            	var baseUrl = window.location.href.substring(0,baseUrlEnd);
                window.location = baseUrl + queryStr;
            } else {
            	window.location = window.location.href + queryStr;
            }
        }
    }
}

if(window.location.href.indexOf("data") > -1){
    var data = window.location.href.substring(window.location.href.indexOf("?data=")+6);
    var splits = data.split(',');
    if(splits.length > 4){
        nPegs = toDecimal(splits[0]);
        minGap = toDecimal(splits[1]);
        maxMoves = toDecimal(splits[2]);
        lineWeight = toDecimal(splits[3]);
        moves = new Array(maxMoves);
        pegs = new Array(nPegs);
		for(var j = 4; j < splits.length; j++) {
			moves[j-4] = toDecimal(splits[j]);
		}
        slider.max = maxMoves;
        slider.value = maxMoves;
        currMove = maxMoves;
        drawLoom();
        
    } else {
    	alert("Invalid Data");
    }
}
   
function toBase87(num) {
	var s = "";
	if(num == 0){
		return "0";
	}
	while(num > 0){
		s += alphabet[num%87];
		num = Math.floor(num/87);
	}
	return s.split("").reverse().join("");
}

function toDecimal(str){
	var rev = str.split("").reverse().join("")
    var num = 0;
    var mul = 1;
    for(var i = 0; i < rev.length; i++){
    	num += alphabet.indexOf(rev[i]) * mul;
    	mul *= 87;
    }
    return num;
}
function logKeydown(e) {
    if(e.key == ' '){
    	togglePlay();
    }
    if(e.key == 'ArrowLeft'){
    	decreaseSpeed();
    }
    if(e.key == 'ArrowRight'){
    	increaseSpeed();
    }
}

function increaseSpeed(){
	speed++;
	if(speed > 10){
		speed = 10;
	}
	speedDisp.innerText = "Speed: "+speed+"x";
}

function decreaseSpeed(){
	speed--;
	if(speed < -10){
		speed = -10;
	}
	speedDisp.innerText = "Speed: "+speed+"x";
}

function togglePlay(){
    if(isPlay){
    	play.src="play.png";//want to pause, change to play symbol
    } else {
    	play.src="pause.png";//want to play, change to pause symbol
    	if((slider.value == slider.max || slider.value == slider.max-1) && speed > 0){
    		currMove = 0;
    		slider.value = currMove;
            ctx.clearRect(0,0,canvas.width,canvas.height);
    		drawLoom()
    	}
    	if(slider.value == slider.min && speed < 0){
    		currMove = maxMoves-1;
    		slider.value = currMove;
            ctx.clearRect(0,0,canvas.width,canvas.height);
    		drawLoom()
    	}
    	window.requestAnimationFrame(animate);
    }
    isPlay = !isPlay;
    slider.style.width = canvas.width - play.offsetWidth - 5 + "px";

}

function animate() {
	ctx.clearRect(0,0,canvas.width,canvas.height);
	drawLoom();
	slider.value = currMove;
	currMove = animationFunc();
	if(isPlay && currMove < maxMoves && currMove >= 0){
		window.requestAnimationFrame(animate);
	} else if(isPlay) {
		if(speed > 0){
			currMove = maxMoves-1;
			slider.value = currMove;
			ctx.clearRect(0,0,canvas.width,canvas.height);
	        drawLoom();
		}
		if(speed < 0){
			currMove = 0;
			slider.value = currMove;
			ctx.clearRect(0,0,canvas.width,canvas.height);
	        drawLoom();
		}
		togglePlay();
	}
}

function resizeCanvas() {
	canvas.width = Math.min(window.innerWidth*scale, window.innerHeight*scale);
	canvas.height = Math.min(window.innerWidth*scale, window.innerHeight*scale);
	div.style.width = canvas.width + "px";
	slider.style.width = canvas.width - play.offsetWidth - 5 + "px";
	drawLoom();
}

function hexToRgb(hex) {
  return hex ? {
    r: parseInt(hex[0]+hex[1], 16),
    g: parseInt(hex[2]+hex[3], 16),
    b: parseInt(hex[4]+hex[5], 16)
  } : null;
}

function drawLoom() {
	ctx.fillStyle = "#000000";
    ctx.strokeStyle = 'rgba('+hexToRgb(color).r+','+hexToRgb(color).g+','+hexToRgb(color).b+','+lineWeight/255+')';
	var angle = 0;
	for(var i = 0; i < nPegs; i++) {
   	    pegX = canvas.width/2 + canvas.width * Math.cos(angle)/2;
		pegY = canvas.height/2 + canvas.height * Math.sin(angle)/2;
		ctx.beginPath();
        ctx.arc(pegX, pegY, 1, 0, Math.PI * 2, true);
        ctx.fill();
		pegs[i] = new Point(pegX, pegY);
		angle+=(2*Math.PI/nPegs);
	}
	ctx.beginPath();
	var currPeg = pegs[moves[0]];
	//ctx.moveTo(currPeg.getX(), currPeg.getY());
	for(var i = 1; i < currMove; i++){
		ctx.moveTo(currPeg.getX(), currPeg.getY());
		currPeg = pegs[moves[i]];
		ctx.lineTo(currPeg.getX(), currPeg.getY());
		ctx.stroke();
		ctx.beginPath();
	}
	if(inExternalFrame){
		ctx.font = "10px Arial";
	    ctx.fillText("mdkar.github.io/PictureLoom", canvas.width-132, canvas.height-2);
	}
}

function copyEmbed(){
	if(document.getElementById('embedText') === null){
		var str = '<iframe id="iframe" height="600" width="600" scrolling="no" style="border:none; overflow:hidden;" onload="loadFrame()" src="';
		str += window.location.href;
		str += '"></iframe>\n<script>\nvar iframe_animation_func = ';
		str += animationFunc.toString();
		str += '\n//format: "params"#show_slider#speed#animation_func#hexcolor\n'
		str += 'var s = "params#' + !isPlay + '#' + speed + '#' + '"+iframe_animation_func.toString()+"#' + color +'";\n'
		str += 'function loadFrame(){\n\tvar frame = document.getElementById("iframe");\n\tframe.contentWindow.postMessage({call:\'sendValue\', value: s}, "*");}\n';
		str += '</script>';
		var el = document.createElement('textarea');
		el.value = str;
		el.id = 'embedText';
		document.getElementById('div2').appendChild(el);
		el.select();
		document.execCommand('copy');
	} else {
		document.getElementById('div2').removeChild(document.getElementById('embedText'));
	}
}
