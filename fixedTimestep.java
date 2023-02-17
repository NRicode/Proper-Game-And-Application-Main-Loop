long currentTime;
long lastTime = System.currentTimeMillis();
float deltaTime = 0;

//60 loop/second
int LOOP_DELAY = 1000 / 60;
boolean quit = false;

while (!quit) {

    currentTime = System.currentTimeMillis();
    deltaTime = ((float)(currentTime - lastTime))/1000; //convert delta time from ms to second

    if (deltaTime * 1000 >= LOOP_DELAY) {

        lastTime = currentTime;
        update(deltaTime)
		render()

    }

    // add a delay to reduce CPU usage
    int timeToWait = LOOP_DELAY - ((int)(deltaTime*1000));
    if (timeToWait > 0) {

        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
