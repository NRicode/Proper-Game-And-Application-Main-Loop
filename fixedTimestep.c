#include <stdio.h>
#include <time.h>
#include <windows.h>

int main() {
	
    clock_t currentTime;
    clock_t lastTime = clock();
    double deltaTime = 0;
	
    //60 loop/second
    const int LOOP_DELAY = 1000 / 60;
    int quit = 0;
	
    while (!quit) {
		
        currentTime = clock();
        deltaTime = (double)(currentTime - lastTime) / CLOCKS_PER_SEC;

        if (deltaTime * 1000 >= LOOP_DELAY) {
			
            lastTime = currentTime;
			update(deltaTime);
            render();
			
        }

        // add a delay to reduce CPU usage
        int timeToWait = LOOP_DELAY - (int)(deltaTime * 1000);
        if (timeToWait > 0) {
			
            Sleep(timeToWait);

        }
		
    }

    return 0;
}
