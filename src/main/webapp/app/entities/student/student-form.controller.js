(function() {
	'use strict'

	angular.module('shopApp').controller('StudentFormController',
			StudentFormController);

	StudentFormController.$inject = [ '$state', '$scope', '$stateParams', 'StudentService' ];

	function StudentFormController($state, $scope, $stateParams, StudentService) {
		var vm = this;

		vm.cancel = cancel;
		vm.save = save;

		vm.student = {
			id : null,
			name : null,
			address : null,
			classes : {
				"id" : null
			}
		}

		vm.classList = [];
		
		if($stateParams.id !== null && $stateParams.id !== undefined){
			StudentService.get({id: $stateParams.id}, function(data){
				vm.student = data;
			}, function(error){
				console.log(error);
			});
		}

		StudentService.getAllClass(function(data) {
			vm.classList = data;
		}, function(error) {
			console.log(error);
		});

		function save() {
			if (vm.student.id != null) {
				StudentService.update(vm.student, function(data) {
					alert('Update Successful!');
					$state.go('^', {reload: true});
				}, function(error) {
					console.log(error);
					alert('Update Fail!');
				});
			} else {
				StudentService.save(vm.student, function(data) {
					alert('Save Successful!');
					$state.go('^', {reload: true});
				}, function(error) {
					console.log(error);
					alert('Save Fail!');
				});
			}
		}

		function cancel() {
			$state.go('^');
		}

	}
})();