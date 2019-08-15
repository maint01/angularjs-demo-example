(function(){
	'use strict'
	
	angular
		.module('shopApp')
		.config(stateConfig);
	
	stateConfig.$inject = ['$stateProvider'];
	
	function stateConfig($stateProvider){
		$stateProvider
			.state('entity', {
				abstract: true
			});
	}
})();