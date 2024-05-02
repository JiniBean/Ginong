const { createApp } = Vue;
import Repository from '/js/module/CartRepository.js';
createApp({
    data(){
        return{
            total:0
        }
    },
    methods:{
        async deleteHandler() {
            const inputs = document.querySelectorAll("input:checked");
            let form = new FormData();
            for (let i of inputs)
                form.append("id", `${i.dataset.id}`);

            console.log(form);
            let repository = new Repository;
            let s = await repository.delete(form);
            console.log(s);
        }
    },

    mounted(){
        const costEl = document.querySelector(".prd");
        const dlvryEl = document.querySelector(".dlvry");

        let cost = parseInt(costEl.dataset.cost);
        let dlvry = parseInt(dlvryEl.dataset.dlvry);

        this.total = cost + dlvry;

    }

}).mount('main');

