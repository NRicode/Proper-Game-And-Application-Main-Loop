# Proper Game And Application Main Loop

There are a lot of resources and examples on how to implement game / application loop such as:
+ https://gamedev.stackexchange.com/questions/1589/when-should-i-use-a-fixed-or-variable-time-step
+ https://gafferongames.com/post/fix_your_timestep/
+ https://gamedev.stackexchange.com/questions/71494/delta-times-and-frame-lag-in-the-game-loop
+ https://blog.sperka.pl/en/2022/07/exact-petla-games-in-c/
+ https://gamedev.stackexchange.com/questions/137658/game-loop-delay-the-right-way-and-game-speed
+ ...and many more

All of those implementation has 1 crucial thing that they missed, it never takes into account the fact that the main `while` loop is actually running without any delay/sleep and eats up alot of CPU.

Variable timestep example:
```
while (!quit)
    {
        //This part is actually ran over and over again without any delay, hogs the cpu
        currentTime = getTicks();
        deltaTime = currentTime - lastTime;
        
        //this part is to limit how many times you want to update() and render() per second
        if (deltaTime >= LOOP_DELAY)
        {
            lastTime = currentTime;
            update();
            render();
        }
    }

```

The proper application loop so that it doesn't eat up too much resource is to limit how much update() is called, however we must also remember to add delay to each loop because even an empty while loop with nothing in it still eats a ton of CPU
```
while(true){
    //open task manager/other app monitor and see how much CPU this empty loop uses.
}
```
# Fixed (With Accumulator) vs Variable Timestep
This repository contains code for fixed timestep (timestep with accumulator) and variable timestep (no accumulator). The main difference is with fixed timestep, whevener a heavy calculation is done / your program lags a little bit it tries to compensate it in the next loop by running your application update() function as needed (the longer the lag the more the compensation). 

With variable timestep, it doesn't try to compensate by running your update() loop multiple times, but rather the delta time is bigger so you can update your application state by multiplying it.

When to use fixed vs variable timestep?
The main advantage of fixed timestep is that it is deterministic. It is good when you want to run something like a physics simulation with consistent result. However, you also need to make sure that the average time to complete 1 loop is less than the desired delay or else this can create the "spiral of death" where the application using a fixed timestep lags behind leading to an infinite increase in the accumulator time. When using fixed timestep it is also possible to create a safeguard with a counter variable that counts how many the the loop is running and breaking from the `while` loop when the variable exceed certain value.
If you don't need consistent result variable timestep is the way to go.
