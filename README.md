<img align="left" src="/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" alt="">

# Fizykor

Fizykor to wzory fizyczne zawsze pod ręką  
  
[![Google Play](https://play.google.com/intl/en_us/badges/images/badge_new.png)](https://play.google.com/store/apps/details?id=com.clakestudio.pc.fizykor)

## About Fizykor
![Back](/images/back.PNG)

Third version of my most popular app relesed in Google Play Store back in 2016 now with new architecutre.  
- [x] Kotlin
- [x] MVVM architecture
- [x] Room persistance Library
- [x] DataBinding library
- [x] RxJava | RxAndroid
- [x] MathView (my own third party view library for kotlin)
## Future of Fizykor
I set myself a goal to publish Fizykor in 2018. Now app is working, but there are several issues that I will add and then solve.
## No RecyclerView solution
I decided to get rid of RecyclerView because of this particular use case where the Database will not grow and the number of needed MathView objects is limited and known at start. Thanks to this approach we use more phones memory but we have smoother user epererience while scrolling.  
  
![Demo NoRecyclerView](/images/gif_no_recycler_view.gif)  
  
With RecyclerView the amount of used memory is smaller, but the user experience while scrolling is horrible.  
  
![Demo RecyclerView](/images/gif_recycler_view.gif)

