#include <stdio.h>
#include <time.h>
#include <windows.h>

int main(){
	
	clock_t currentTime;
	clock_t lastTime = clock();
	double deltaTime = 0;
	float accumulator = 0.0f;
	
	//60 loop/second
	const int LOOP_DELAY = 1000 / 60;
	const float TIME_STEP = 1.0f / 60.0f;
	int quit = 0;
	
	while (!quit) {
		
		currentTime = clock();
		deltaTime = (double)(currentTime - lastTime) / CLOCKS_PER_SEC;
		lastTime = currentTime;

		accumulator += deltaTime;

		while (accumulator >= TIME_STEP) {
			
			update(TIME_STEP);
			accumulator -= TIME_STEP;
			
		}

		render();
		
		// add a delay to reduce CPU usage
		int remainingFrameTime = LOOP_DELAY - (int)(deltaTime * 1000.0);
		if (remainingFrameTime > 0) {
			
			Sleep(remainingFrameTime);
			
		}
		
	}
	
	return 0;
}