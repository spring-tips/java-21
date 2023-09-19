# Java 21

## script for video 
No Spring fans! Let's not live that drama. Java 21 is available as of the release of this video, and with it come a
cornucopia of capabilities that, quite simply, are too good to ignore.

In this installment we're going to look at some of the amazing features that have sort of come together in their final
form in Java 21. These are not all new necessarily in Java 21, but they're certainly new relative to the (in my mind, at
least) Java 17 release LTR release that preceded it.

* multiline strings - MultilineStringTest
* records - RecordTest
* enhanced switch - EnhancedSwitchTest
* technically sealed types are part of Java 17 too but they don't buy you much, yet.. SHow the `Java17Test.java`, but
  change things to use `sealed` and `permits` and `final`
* smart casting `instanceof` test
* the smart casting is great, but that test for the types seems kind of tortured.
* thankfully we can merge two of our new toys together in new and interesting ways in Java 21. Lets see how the enhanced
  switch can be used with pattern matching to greatly simplify this code. (show swithc for the types) but be sure to
  include a default branch.
* And indeed, if you use sealed types, this code becomes even cleaner! now the compiler can guarantee exhaustiveness,
  assuring us that there are no branches that we havent covered. as soon as we add a new class, the compiler will bark
  that we havent handled the class. Nice!
* what we're doing there, using pattern matching to detect a particular type, is really powerful. and it even has a nice
  new interaction when used with records. Lets look at a slightly expanded version of our records example from earlier.
  Here we're dealing with events. but we can extract the components of the record in our enhanced switch, because the
  compiler knows about the internal state and knows that all thins we describe are part of the public API.
* All thse things started to take root in earlier versions of Java but culminate here in Java 21 in what you might call
  data oriented programming. not a replacement for object oriented programming, but a compliment to. you can use things
  like pattern matching, enhanced switch, and the instanceof operator to give your code a new kind of polymorphism
  without having to expose the dispatch point in your public api.
* Nice! I love java 21. and of course thees arent the only things new in java 21. there's a bunch of small but nice
  things and of course, project loom or virtual threads. virtual threads alone are worth the price of admission. let's
  dive right into some of these amazing features.
* In the world of AI and algorithms, efficient maths are more important than ever. The new JDK has some nice
  improvements here including parallel multiplication for BigIntegers and various overloads for divisons that throw an
  exception if there's an overflow. Not just if there's a divide by zero error.
* If you're doing asynchronous programming (yes, that's still a thing, even with project Loom, you'll be pleased to know
  our old friend `Future<T>` now makes available a state object that you can then switch on to see the status of the
  ongoing operation..)
* One place you might want to wrap async operation in a future, and use Project Loom, is the HTTP client api. the HTTP
  Client API has existed since java 11, which is now a full ten releases in the distant distant past! But, now it has
  this spiffy new autocloseable api.
* make sure, of course, that yo udon't use the autocloseable api in conjunction with a future, because it might get
  closed before you finish using it in the other thread of execution.
* In that example, i used `HttpResponse.BodyHandlers.ofString` to get a `String` response back. You can get all sorts of
  objects back, not just Strings. But Strings are nice because theyre a great segue to another amazing feature in Java
  21, which is that java 21 includes support for emojis! It's a nice, but small, thing. In this example we're simply
  testing for the presence of an emoji in a string.
* There's also a new ranged indexOf test, letting you specify the beginning and end of the string you'd like to search.
* and there's now a built in way in the jdk to repeat a character. Can we throw away all those `StringUtils` classes
  yet?
* And if you're parsing string data, you'll be pleased to know there's a new part of the API for regular expressions,
  called `MatchResult`, that you can inspect for the results of a match. It's nicety, not a necessity.
* and if u want order those strings in a collection, youll want an ordered collection. The JDK has a few of them,
  LinkedHashMap, List, etc., but they didn't have a common ancestor. Now they do: welcome, SequencedCollections! in this
  example we work with both a simple ArrayList and we use the fancy new factory methods for things like a LinkedHashSet.
  This new constructor is nice because it does some math internally to guarantee that it won't have to do a rebalance (
  and thus rehash everything) before you've added as many elements as you've stipulated in the constructor.
* and finally, we get to Loom. Youve heard a lot about this. but teh basic idea is to make the code you wrote in college.. scale!  
* what do i mean b that? lets write a simplistic network service that just prints out whatever is given to us. well need to read in from one inputstream and accrue everything into a new buffer. then prit it out once it's all done. the problem is we might get a lot of data at the same time. so we use threads. but here again, we're defeated, because - prioro to java 21 - threads are expensive! they cost about 2mb of ram for each thread. and so we pool them in a thread pool and reuse them. but even there, if we have too many requests, we'll end up in a situation where none of the threads in the pool are available they're all being used. Well, sort of. Theyre statistically not being used, but they're not available for use either. you see, in this example the threads are blocked. theyre probably waiting for data from the client. the client might take its time. it might send a lot of data over a slow network. it might become disconnected. and the unfortunate state of things is that the server, waiting on that data, has no choice but to sit there, parked on a thread, not allowing anybody else to use it. until now. Java 21 brings with it virtual threads. now, we can create millions of threads for heap. it's easy. but fundamentally the facts on the ground are that real threads are expensive. so, how can the jdk let us have millions of threads? Simple, it has a greatly improved runtime that now notices when were blocked and suspends execution on the thread until the thing we're waiting for is finished. then it quietly puts us back on another thread. the real threads act as carriers for virtual threads, allowing us the illusion of millions of threads. Java 21 has improvements in all the places that historically block threads, like blocking IO with InputStream and OutputStream , and Thread.sleep, so now they correctly signal to the runtime that its ok to reclaim the thread and repurpose it for othe virtual threads, allowing work to progress even when a virtual thread is 'blocked'. you can see that in this example here, that i shamelessly stole from José Paumard, one of the java developer advocates at oracle whose work i love. in it, we launch a lot of threads, to the point where we'll need to start sharing carrier threads, and then in them we sleep. which would normally block. but not in virtual threads. we'll sample one of the threads (the first one) and note the name of the threads on which it's running before and after each sleep. Notice that they've changed! The runtime has moved us on and off carrier threads, with no changes to our code! And that's basically the magic of Project Looom. No code scalability on par with wht you'd get with reactive programming. What about our network iO? we do require _one _ change. But it's basically a very simple one. Swap out the threadpool. Like this. 
* Spring Boot applications typically have a lot of Executors in play for all sorts of things, like integration, messaging, web services, etc. If youre usng Spring Boot 3.2, coming out ni November of 2023, and Java 21, then you can use this newp roperty and spring boot will automatically plugin virtual thread pools for you! Neat.
* Java 21 is a hgue deal. Syntax on part with many other much more modern languages and scalability that's as good or better than alot of modern languages without complicated the code with things like async/await, reactive programming, etc.
* And of course, if you want a native imaget here is also the graalvm project, which provides an AOT compile that will also release today, September 19th, 2023. You can use this to compile your highly scalable Boot applications into GralVM native images that start in no time and take a tiny fractio of the ram they took on the JVM. 
* It's never been a better time ot be a developer on the JVM! 


## script for telenovela 
- MANAGER: Javito Risueño! Bienvenido al equipo. Te va a encantar!
- JAVITO: Gracias, licenciado.
- Nuestro CEO tiene una idea que revolucionará la industria del turismo para siempre. Listo?
  Empacamos tus maletas.
- JAVITO: ... eh?
- MANAGER: Sí, cuando vas a viajar, mandamos una persona a tu casa y empaca tus maletas. A nadie le gusta empacar, y siempre olvidas algo.
  Pero con nuestro servicio, eso ya no pasará.
- JAVITO: mmmmm... [no está convencido] 
- MANAGER: Se llama... MALETIA.
- JAVITO: [ve a la cámara, se está preocupando]
- JAVITO: eh, y qué versión de Java están usando?
- MANAGER: Excelente pregunta! Todos nuestros sistemas corren en... [se acerca a la cámara] Java 7.
- JAVITO: [abre los ojos, sorprendido]
- MANAGER: ...y struts... uno.
- JAVITO: [cara de espanto]
- MANAGER: Pero por qué esa cara? El siete es de buena suerte.
- JAVITO: ...y el uno?
- MANAGER: Uno no es ninguno.
- JAVITO: Eh, sabe qué, licenciado? Me acabo de acordar que dejé prendida la estufa. Me tengo que ir! Hasta luego, y gracias por todo! [cuelga]
- MANAGER: [ve a la cámara] él se lo pierde.





## English
- MANAGER: Javito Risueño! Welcome to the team. You're gonna love it here!
- JAVITO: Thank you, sir.
- Our CEO has an idea that will revolutionize the travel industry forever. Are you ready?
  We pack your bags.
- JAVITO: ... huh?
- MANAGER: Yeah! when you're going on a trip, we send someone to your place to pack your bags for you.
  Nobody likes to pack, and you always forget something. But with a service, that will be a thing of the past.
- JAVITO: mmmmm... [seems unconvinced] 
- MANAGER: It's called... MALETIA.
- JAVITO: [stares at the camera, concerned]
  uh, and what version of Java are you using?
- MANAGER: That's an excellent question! All our systems run on... [closer to camera] Java 7.
- JAVITO: [eyes wide open in surprise]
- MANAGER: ...and struts... one.
- JAVITO: [scared face]
- MANAGER: Why that face? Seven is a lucky number.
- JAVITO: um, you know what, sir? I just remembered I left the stove on. I have to go! So long, and thanks for everything! [hangs up]
- MANAGER: [looks at camera, shrugs] his loss.