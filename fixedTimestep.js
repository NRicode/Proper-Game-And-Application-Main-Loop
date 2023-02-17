let lastRender = 0;
function loop(timestamp) {
	
  let delta = timestamp - lastRender

  console.log(delta);

  lastRender = timestamp
  requestAnimationFrame(loop)
}
requestAnimationFrame(loop);