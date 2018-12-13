# Fizykor3
Third version of an app I created back in 2016 .
## About Fizykor3
Third version of my most popular app relesed in Google Play Store back in 2016 now with new architecutre.  
* Kotlin
* MVVM architecture
* Room persistance Library
* DataBinding library
* RxJava | RxAndroid
* MathView (my own third party view library for kotlin)
## Publication
After adding all needed features the app will be again available in Google Play Store. Expected time of publication is December 2018.
## No RecyclerView solution
I decided to get rid of RecyclerView because of this particular use case where the Database will not grow and the number of needed MathView objects is limited and known at start. Thanks to this approach we use more phones memory but we have smoother user epererience while scrolling.
![Demo NoRecyclerView](/gif_no_recycler_view.gif)
With RecyclerView the amount of used memory is smaller, but the user experience while scrolling is horrible.
![Demo RecyclerView](/gif_recycler_view.gif)

