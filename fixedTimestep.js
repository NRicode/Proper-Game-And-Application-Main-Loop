let lastRender = 0;
function loop(timestamp) {
	
  let delta = (timestamp - lastRender)/1000 //convert delta from ms to second

  update(delta)
  render()

  lastRender = timestamp
  requestAnimationFrame(loop)
}
requestAnimationFrame(loop);
