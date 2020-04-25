var canvas = document.getElementById('canvas1');
var div = document.getElementById('div1');
var play = document.getElementById('play');
var slider = document.getElementById('slider');
canvas.width = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
canvas.height = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
div.style.width = canvas.width + "px";
slider.style.width = canvas.width - play.offsetWidth - 5 + "px";
var width = canvas.width;
var height = canvas.height;
var ctx = canvas.getContext('2d');
var nPegs;
var minGap;
var maxMoves;
var lineWeight;
var pegs;
var moves;
var currMove;
var isPlay = false;

document.addEventListener('keydown', logKeydown);

if(window.location.href.indexOf("data") > -1){
    var data = getUrlVars()["data"];
    var splits = data.split(',');
    if(splits.length > 4){
        nPegs = toDecimal(splits[0]);
        minGap = toDecimal(splits[1]);
        maxMoves = toDecimal(splits[2]);
        lineWeight = toDecimal(splits[3]);
        slider.max = maxMoves;
        
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

var alphabet = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_';
   
function toBase64(num) {
	var s = "";
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
}

function togglePlay(){
    if(isPlay){
    	play.value="\u25B6";
    } else {
    	play.value="\u23F8";
    }
    isPlay = !isPlay;
    slider.style.width = canvas.width - play.offsetWidth - 5 + "px";
}

function resizeCanvas() {
	canvas.width = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
	canvas.height = Math.min(window.innerWidth*0.8, window.innerHeight*0.8);
	div.style.width = canvas.width + "px";
	width = canvas.width;
	height = canvas.height;
	drawLoom();
}

function drawLoom() {
   
}
