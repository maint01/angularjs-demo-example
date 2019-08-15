(function() {
    'use strict';

    angular
        .module('customerInfoApp')
        .controller('CustomerInfoDialogController', CustomerInfoDialogController);

    CustomerInfoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CustomerInfo'];

    function CustomerInfoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CustomerInfo) {
        var vm = this;

        vm.customerInfo = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.customerInfo.id !== null) {
                CustomerInfo.update(vm.customerInfo, onSaveSuccess, onSaveError);
            } else {
                CustomerInfo.save(vm.customerInfo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('customerInfoApp:customerInfoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datePerform = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
