
const { createApp } = Vue;

createApp({
    data() {
        return {
            info: [],
            images: [
                '/img/baechoo.png'
                ,'/img/blueBerry.jpg'
                ,'/img/carrot.png'
                ,'/img/gochujang.jpg'
                ,'/img/gochujangCucumber.jpg'
                ,'/img/greenOnionKimchi.png'
                ,'/img/pineapple.jpg'
                ,'/img/saltedFish.png'
                ,'/img/seasonedCucumber.jpg'
            ],
            names: [],
            state: [],
            length: 1,
            selected: 0
        }
    },
    async created(){
        await this.fetchProducts();
    },
    methods: {
        async fetchProducts() {
            let response = await fetch(`/api/member/orderInfo`);
            let info = await response.json();
            this.info = info;

            response = await fetch(`/api/member/categoryList`);
            let state = await response.json();
            this.state = state;


            response = await fetch(`/api/member/pickProductList`);
            let pickProductList = await response.json();
            this.processProducts(pickProductList);
        },
        processProducts(pickProductList) {
            const limitedProductList = pickProductList.slice(0, 9);
            limitedProductList.forEach(product => {
                // this.images.push(product.thumbnailPath+'/'+product.thumbnailName);
                this.names.push(product.name);
            });
            this.length = Math.ceil(this.images.length / 3)-1;
        },
        next() {
            if (this.selected < this.length) {
                this.selected += 1;
            }
        },
        pre() {
            if (this.selected >= 1) {
                this.selected -= 1;
            }
        }
    }
}).mount('main');
