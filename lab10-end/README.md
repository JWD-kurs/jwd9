## Lab 10 - custom servisi, modularizacija

----

### Pravljenje custom servisa

U AngularJS, servis predstavlja objekat koji obavlja specifičan zadatak (npr. $log za logovanje, $http za komunikaciju preko HTTP protokola),
a povezuje se sa ostatkom aplikacije pomoćuje injekcije (dependency injection). Servisi enkapsuliraju kod koji se koristi u direktivama i kontrolerima.

AngularJS nudi ima veliki broj servisa, ali često bude potrebno praviti novi, svoj, custom servis. 
Pravljenjem custom servisa izbegava se dupliciranje koda i smanjuje se složenost programa.

Primer pravljenja custom servisa:

```javascript
app.service('myService', function() {
	
	this.myServiceFunction = function() {
		return 'some text';
	};
});
```

Primer injektovanja i pozivanja custom servisa:

```javascript
app.controller('MyControler', function($scope, myService) {
	
	$scope.myControllerFunction = function() {
		$scope.text = myService.myServiceFunction();
	};
});
```

Obratiti pažnju da se servis OBAVEZNO injektuje u kontroler u kojem se koristi kao parametar funkcije (function($scope, myService) { ... }).
Takođe, custom servisi nemaju $ na početku imena, za razliku od AngularJS ugrađenih servisa.


* Definisati custom servis "activityRestService" koji će enkapsulirati čitavu funkcionalnost vezanu za dobavljanje, brisanje i čuvanje aktivnosti preko REST servera.

----

### Modularizacija komponenti AngularJS aplikacije u zasebne fajlove

* Razdvojiti aplikaciju u više modula, gde će svaki modul biti zasebni JavaScript fajl.

* Napraviti fajlove app.controllers.js, app.directives.js i app.services.js. U ove fajlove prebaciti odgovarajuće funkcionalnosti. OBAVEZNO dodati učitavanje ovih fajlova u index.html.