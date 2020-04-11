class ComplexNumber {
	constructor(a, b) {
		this.a = a;
		this.b = b;
	}
	mag() {
		return(Math.sqrt(this.a*this.a+this.b*this.b));
	}
	times(z) {
		return(new ComplexNumber(this.a*z.a-this.b*z.b,this.a*z.b+this.b*z.a));
	}
	plus(z) {
		return(new ComplexNumber(this.a+z.a, this.b+z.b));
	}

}

var canvas = document.getElementById('canvas1');
canvas.width = Math.min(window.innerWidth, window.innerHeight);
canvas.height = Math.min(window.innerWidth, window.innerHeight);
var width = canvas.width;
var height = canvas.height;
var ctx = canvas.getContext('2d');
canvas.addEventListener("click", positionZoom, false);
canvas.addEventListener('contextmenu', function(ev) {
    ev.preventDefault();
    zoom(ev.clientY, ev.clientX, 0.5);
    return false;
}, false);
document.addEventListener('keydown', logKeydown);
//document.addEventListener('keyup', logKeyup);
//var img = ctx.createImageData(width, height);
var maxIter = 100;
var maxMag = 100;
var maxx = 2;
var maxy = 2;
var minx = -2;
var miny = -2;

function positionZoom(e) {
  zoom(e.clientY, e.clientX, 2);
}

function logKeydown(e) {
	if(event.shiftKey){
		switch(e.key) {
		    case '{':
		        maxMag-=10;
			    drawSet();
		        break;
		    case '}':
		        maxMag+=10;
			    drawSet();
		        break;
		    case 'ArrowUp':
		        e.preventDefault();
		        zoom(width/2-10,height/2,1);
		        break;
		    case 'ArrowDown':
		        e.preventDefault();
			    zoom(width/2+10,height/2,1);        
		        break;		
            case 'ArrowLeft':
		        e.preventDefault();
		        zoom(width/2,height/2-10,1);
		        break;
		    case 'ArrowRight':
		        e.preventDefault();
		        zoom(width/2,height/2+10,1);
		        break;

	    }

	} else {
		console.log(e.key);
		switch(e.key) {
		    case '[':
		        maxIter-=10;
			    drawSet();
		        break;
		    case ']':
		        maxIter+=10;
			    drawSet();
		        break;
		    case 'ArrowUp':
		        e.preventDefault();
		        zoom(width/2,height/2,1.1);
		        break;
		    case 'ArrowDown':
		        e.preventDefault();
		        zoom(width/2,height/2,1/1.1);
		        break;		    

	    }
	}
}

function hsv_to_rgb(h, s, v)
{
  if ( v > 1.0 ) v = 1.0;
  var hp = h/60.0;
  var c = v * s;
  var x = c*(1 - Math.abs((hp % 2) - 1));
  var rgb = [0,0,0];

  if ( 0<=hp && hp<1 ) rgb = [c, x, 0];
  if ( 1<=hp && hp<2 ) rgb = [x, c, 0];
  if ( 2<=hp && hp<3 ) rgb = [0, c, x];
  if ( 3<=hp && hp<4 ) rgb = [0, x, c];
  if ( 4<=hp && hp<5 ) rgb = [x, 0, c];
  if ( 5<=hp && hp<6 ) rgb = [c, 0, x];

  var m = v - c;
  rgb[0] += m;
  rgb[1] += m;
  rgb[2] += m;

  rgb[0] *= 255;
  rgb[1] *= 255;
  rgb[2] *= 255;
  return rgb;
}

function zoom(x, y, scale) {
	var newDist = (maxx-minx)/(2*scale);
	x = itransformX(x);
	y = itransformY(y);
	maxx = x + newDist;
	minx = x - newDist;
	maxy = y + newDist;
	miny = y - newDist;
	drawSet();
}

function resizeCanvas() {
	canvas.width = Math.min(window.innerWidth, window.innerHeight);
	canvas.height = Math.min(window.innerWidth, window.innerHeight);
	width = canvas.width;
	height = canvas.height;
	drawSet();
}

function drawSet() {
  //console.log(canvas.width);
  var img = ctx.createImageData(canvas.width, canvas.height);
  var set = 0;
  for(i = minx; i<= maxx; i+=((maxx-minx)/width)) {
  	for(j = miny; j<= maxy; j+=((maxy-miny)/height)) {
  	  var color = getPoint(j,i);
      img.data[set++] = color[0];
      img.data[set++] = color[1];
      img.data[set++] = color[2];
      img.data[set++] = 255;
      if((set/4)%width == 0){
   	  	break;
   	  }
    }
  }
  ctx.putImageData(img, 0, 0);
}

function transformX(i) {
  return (canvas.width) * (i - minx) / (maxx - minx);
}
function itransformX(x) {
  return ((maxx-minx) * x / canvas.width) + minx;
}
function itransformY(y) {
  return ((maxy-miny) * y / canvas.height) + miny;
}
function transformY(j) {
  return (canvas.height) * (j - miny) / (maxy - miny);
}

function getPoint(i, j) {
  var z = new ComplexNumber(0,0);
  var c = new ComplexNumber(i,j);
  var mag = 0;
  var n = 0;
  while(n<maxIter && mag<maxMag) {
    z = (z.times(z)).plus(c);
    mag = z.mag();
    n++;
  }
  if(n>maxIter-1)
    return([0,0,0]);
  return(hsv_to_rgb(360*n/maxIter, 1, 1));
}
