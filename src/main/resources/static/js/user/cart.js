const { createApp } = Vue;
// import repository from '/js/module/CartRepository.js';
createApp({
    data(){
    },
    methods:{
        deleteHandler(){
            const inputs = document.querySelectorAll("input:checked");
            let list = new Array();
            for(let i of inputs)
                list.push(i.dataset.id);

            // repository.delete(list);
        }
    }
}).mount('main');

