const { createApp } = Vue


createApp({
    data() {
        return {
            tabIndex: 0,
        }
    },
    methods:{
        // 탭바꾸기
        clickTab(selectedIndex) {
            this.tabIndex = selectedIndex;
        },
        toggleArrow(e) {
            const arrow = e.currentTarget.querySelector('.arrow');
            arrow.classList.toggle('rotate-180');
        }

    },
}).mount('main');


