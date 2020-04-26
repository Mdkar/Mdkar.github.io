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

var canvas = document.getElementById('canvas1');
var div = document.getElementById('div1');
var play = document.getElementById('play');
var slider = document.getElementById('slider');
var file = document.getElementById('file1');
var speedDisp = document.getElementById('speed');
canvas.width = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
canvas.height = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
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
var alphabet = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_';

document.addEventListener('keydown', logKeydown);
file.addEventListener('change', handleFileSelect, false);
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
                queryStr += toBase64(parseInt(fileSplits[i]));
                queryStr += ',';
            }
            queryStr = queryStr.substring(0,queryStr.length-2);
            window.location = window.location.href + queryStr;
        }
    }
}

if(window.location.href.indexOf("data") > -1){
    var data = getUrlVars()["data"];
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

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
   
function toBase64(num) {
	var s = "";
	if(num == 0){
		return "0";
	}
	while(num > 0){
		s += alphabet[num%64];
		num = Math.floor(num/64);
	}
	return s.split("").reverse().join("");
}

function toDecimal(str){
	var rev = str.split("").reverse().join("")
    var num = 0;
    var mul = 1;
    for(var i = 0; i < rev.length; i++){
    	num += alphabet.indexOf(rev[i]) * mul;
    	mul *= 64;
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
	currMove += speed;
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
	canvas.width = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
	canvas.height = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
	div.style.width = canvas.width + "px";
	slider.style.width = canvas.width - play.offsetWidth - 5 + "px";
	drawLoom();
}

function drawLoom() {
	ctx.fillStyle = "#000000";
    ctx.strokeStyle = 'rgba(0,0,0,'+lineWeight/255+')';
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
	
}
