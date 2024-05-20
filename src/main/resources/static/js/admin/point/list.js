const {createApp} = Vue;
import Repository from "/js/module/PointRepository.js";

createApp({
    data() {
        return {
            list: [],
        }
    },
    methods: {
        async getList(orderType, sortType) {
            this.orderType = orderType;
            this.sortType = sortType
            let repository = new Repository;
            this.list = await repository.findAll(this.query, sortType, false);

            // switch (orderType) {
            //     case 1:
            //         this.list = await repository.findAll(this.query,sortType,false);
            //         break;
            //     case 2:
            //         this.list = await repository.findExRef(this.query,sortType,false,1);
            //         break;
            //     case 3:
            //         this.list = await repository.findExRef(this.query,sortType,false,2)
            //         break;
            //     case 4:
            //         this.list = await repository.findCancel(this.query,false)
            //
            // }

        },
        test(){
            console.log(this.list);
        }
    },
    mounted() {
        // this.getList();
        // this.test();

        this.getList().then(() => {
            this.test();
        });
    }
}).mount('main');