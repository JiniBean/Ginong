const { createApp } = Vue

createApp({
    data() {
        return {
            list: [],
        }
    },
    methods: {
        goDetail(notice) {
            let noticeId = notice.id;
            location.href = `/admin/notice/detail?noticeId=${noticeId}`;
        },
    },
    async created() {
        let response = await fetch(`/api/notices`);
        let list = await response.json();
        this.list = list;

        for (notice of list) {
            const dateIdx = notice.regDate.search("T");
            const subDate = notice.regDate.substring(0, dateIdx);
            notice.regDate = subDate;
        }
        // let response = await fetch(`/api/notices`);
        // let list = await response.json();
        
        // for (item of list) {
        //     // const dateIdx = item.regDate.search("T");            
        //     // const subDate = item.startDate.substring(0, dateIdx);
        //     // item.startDate = subDate;
        //     // console.log(item.startDate);
            
        // }
    }
}).mount('main');

