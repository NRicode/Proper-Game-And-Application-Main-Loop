//60 loop/second
//LOOP_DELAY here is not really any delay, it's just to check whether our accumulator has went through our defined time and repeat the update() CODE
const LOOP_DELAY = 1000 / 60;
const TIME_STEP = 1.0f / 60;
let lastRender = 0;
let accumulator = 0;
let firstLoop = true;
function loop(timestamp) {
	
  let delta;
  
  if(firstLoop){
	  
	firstLoop = false;
	lastRender = timestamp;
	delta = 0;
	
  }else{
	  
	delta = timestamp - lastRender;
	
  }
  
  lastRender = timestamp;
  accumulator += delta; 

  while (accumulator >= LOOP_DELAY) {

	update(TIME_STEP)
    accumulator -= LOOP_DELAY; 
	
  }
  
  draw();

  requestAnimationFrame(loop);
}

requestAnimationFrame(loop); 