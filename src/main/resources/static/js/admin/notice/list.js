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

        goReg() {
            location.href = `/admin/notice/reg`;
        },
    },
    async created() {
        let response = await fetch(`/api/notices`);
        let list = await response.json();
        this.list = list;

        for (let notice of list) {
            let dateIdx = notice.startDate.search("T");
            let subDate = notice.startDate.substring(0, dateIdx);
            notice.startDate = subDate;

            dateIdx = notice.endDate.search("T");
            subDate = notice.endDate.substring(0, dateIdx);
            notice.endDate = subDate;

        }
    }
}).mount('main');

