long currentTime;
long lastTime = System.currentTimeMillis();
long deltaTime = 0;

//60 loop/second
long LOOP_DELAY = 1000 / 60;
boolean quit = false;

while (!quit) {
	
    currentTime = System.currentTimeMillis();
    deltaTime = currentTime - lastTime;

    if (deltaTime >= LOOP_DELAY) {
		
        lastTime = currentTime;
        update(deltaTime / 1000.0);
		render();
		
		
    }

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