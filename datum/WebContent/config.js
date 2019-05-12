App.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise("/home");
	// Now set up the states
	$stateProvider.state('home',{
		url : "/home",
		templateUrl :"list.html"									
	}).state('add', {
		url : "/add",
		templateUrl : "newUser.html"
	}).state('back', {
		url : "/back",
		templateUrl : "check.html"
	}).state('edit', {
		url : "/edit",
		templateUrl : "	newUser.html"
	})
	.state('delete', {
		url : "/delete",
		templateUrl : "list.html"
	})
	.state('save', {
		url : "/save",
		templateUrl : "list.html"
	})
	
});