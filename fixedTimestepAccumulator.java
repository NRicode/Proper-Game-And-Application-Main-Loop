long currentTime;
long lastTime = System.currentTimeMillis();
long deltaTime = 0;
float accumulator = 0.0f;

//60 loop/second
long LOOP_DELAY = 1000 / 60;
float TIME_STEP = 1.0f / 60;
boolean quit = false;

while (!quit) {
	
	currentTime = System.currentTimeMillis();
	deltaTime = currentTime - lastTime;
	lastTime = currentTime;

	accumulator += deltaTime / 1000.0f;

	while (accumulator >= TIME_STEP) {
		
		update(TIME_STEP);
		accumulator -= TIME_STEP;
		
	}
	
	render();

	// add a delay to reduce CPU usage
	long timeToWait = LOOP_DELAY - deltaTime;
	if (timeToWait > 0) {
		
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}