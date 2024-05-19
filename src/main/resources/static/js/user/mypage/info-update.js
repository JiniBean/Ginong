const { createApp } = Vue;

createApp({
    data() {
        return{
            currentTab : 'info',
            birthDate : '',
            memberList : [],
            locationList : [],
            defaultIctList : []
        }
    }
    ,methods : {
        formatDate(dateString) {
            // 날짜 문자열을 Date 객체로 변환
            const date = new Date(dateString);
            // 년, 월, 일 추출
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            // yyyy-MM-dd 형식으로 반환
            return `${year}-${month}-${day}`;
        }
    }
    , async created(){

        try {
            // 세 개의 API 호출을 병렬로 실행
            const [infoResponse,defaultIctResponse , lctResponse] = await Promise.all([
                fetch("/api/member/userInfo"),
                fetch("/api/member/location/defaultList"),
                fetch("/api/member/location/list")
            ]);

            // 두 개의 응답을 JSON으로 변환
            const [infoList, defaultIctList, lctList] = await Promise.all([
                infoResponse.json(),
                defaultIctResponse.json(),
                lctResponse.json()
            ]);

            // 데이터를 Vue 인스턴스의 데이터 속성에 할당
            this.memberList = infoList;
            this.locationList = lctList;
            this.defaultIctList = defaultIctList;

            console.log("================", defaultIctList);

        } catch (error) {
            console.error("Failed to fetch data:", error);
        }
    }
}).mount('main');