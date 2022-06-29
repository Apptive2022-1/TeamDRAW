package com.example.teamdraw.util

import com.example.teamdraw.models.Contest
import com.example.teamdraw.models.User

class ProvideUserData {
    companion object {
        val user1 = User(
            userId = "abc",
            name = "신성현",
            nickname = "까마귀",
            sex = "남성",
            positionList = mutableListOf("개발자"),
            selfIntroduce = " 안녕하세요 신성현입니다. 잘부탁드립니다 열심히하겠습니다~~!~!~!!~!~!~!~!"
        )
        val user2 = User(
            userId = "bcd",
            name = "정수현",
            nickname = "까치",
            sex = "여성",
            positionList = mutableListOf("개발자"),
            selfIntroduce = "까치가 좋아요오ㅗ오ㅗ오오오오"
        )
        val user3 = User(
            userId = "edas",
            name = "김잼민",
            nickname = "잼민이",
            sex = "남성",
            positionList = mutableListOf("디자이너"),
            selfIntroduce = " 잼민이 잼민 김잼민"
        )
        val user4 = User(
            userId = "wqe",
            name = "주다은",
            nickname = "다은주",
            sex = "여성",
            positionList = mutableListOf("기획자"),
            selfIntroduce = " 주다은주다은주다은주다은주다은주다은주다은주다은주다은주다은주다은주다은"
        )
        val user5 = User(
            userId = "joo",
            name = "이주빈",
            nickname = "joo",
            sex = "여성",
            positionList = mutableListOf("기획자"),
            selfIntroduce = "이이이이이이이ㅣ이이이잊우주주주주줒비비비니비ㅣㄴ비빈빈비니비"
        )
        val user6 = User(
            userId = "qzzz",
            name = "최이경",
            nickname = "lleee",
            sex = "여성",
            positionList = mutableListOf("기획자"),
            selfIntroduce = " 누가 이 코드 보냐?"
        )
        val user7 = User(
            userId = "yibhj",
            name = "김연호",
            nickname = "YOONIHO",
            sex = "남성",
            positionList = mutableListOf("디자이너"),
            selfIntroduce = "셀프셀프인트로듀스슷드스드ㅡㅅ디스듸슷디ㅡㅅ드스스스슷스스스"
        )
        val userList = listOf<User>(user1, user2, user3, user4, user5, user6, user7)
    }
}

class ProvideDataContest {
    companion object {
        val contest = Contest(
            contestId = 0L,
            title = "아이더 로고 디자인 공모전",
            field = "디자인 사진 예술 영상",
            period = "2022. 01. 08 ~ 2022. 01. 30",
            organization = listOf("아이더", "그라폴리오"),
            reward = "최대 100만원",
            detail = """
                YOUNG OUTDOOR EIDER
                아이더 제품에 잘 어울릴만한 로고를 디자인해주세요
                    
                접수기간 : 1.08(수) ~ 1.30(목)
                투표기간 : 1.08(수)~ 2.06(목)
                당첨 발표 : 2.13(목)
                    
                대상(1 명) : 500만원
                우수상(5 명) : 100만원
                    """.trimIndent()
        )
        val contest1 = Contest(
            contestId = 1L,
            title = "제2회 웅진씽크빅 게임 개발 챌린지",
            field = "개발 게임 기획 디자인",
            period = "2022. 06. 28 ~ 2022. 07. 31",
            organization = listOf("웅진씽크빅", "한국콘텐츠진흥원"),
            reward = "최대 3000만원",
            detail = """
                매쓰피드 문항 출제 API를 활용한 게임 앱 개발
                창의적이며 혁신적인 아이디어 및 기술을 보유한 누구나 (청소년 포함)
                
                총 5,400만 원 상당의 상금
                * 웅진씽크빅상 (1팀) : 3,000만원
                * ㈜웅진상 (1팀) : 1,000만원
                * 우수상(3팀) : 300만원
                * 장려상(1팀) : 100만원- 최종 수상 10팀 혜택
                * 수상자 웅진씽크빅 채용 우대 및 팀 단위 공동 개발 지원
                * 청소년 부문 웅진씽크빅 장학금 제공
                
                [기간 및 일정]
                - 온라인 밋업 : 7월 12일(화)
                - 예선 응모 일정 : 6월 28일(화) – 7월 31일(일)
                - 본선 진출팀 발표 : 8월 5일(금)
                - 본선 개발 기간 : 8월 5일 – 11월 20일
                - 결승 진출팀 발표 : 11월 30일(수)
                - 결승전 : 12월 7일(수)
                
                [문의처]- 운영 사무국 : wjtb.challenge@gmail.com
                    """.trimIndent()
        )
        val contest2 = Contest(
            contestId = 2L,
            title = "입자 형태 분석 모델 개발 해커톤",
            field = "모델링 개발  화학  ML",
            period = "2022. 06. 27 ~ 2022. 08. 08",
            organization = listOf("(주)인공지능팩토리", "LG 화학"),
            reward = "최대 300만원",
            detail = """
                유체상에 떠다니는 입자를 촬영한 화상을 바탕으로 각 입자와 그 형상을 검출해내는 Instance Segmentation 모델개발
                    
                기간 및 일정
                - 참가자 접수 (대회 기간 참가자 상시 모집) : 6/27 (월) ~ 8/8 (월)
                - 대회 기간 : 7/7 (목) 8:00 ~ 8/8 (월) 18:00
                - 입상자 결과 발표 : 8/19 (금) 
                    
                상금 (총 상금 500만원)
                - 대상 1팀 : 300만원
                - 우수상 1팀 : 150만원
                - 장려상 1팀 : 50만원
                    """.trimIndent()
        )
        val contest3 = Contest(
            contestId = 3L,
            title = " 2022 피란수도 부산 시민아카데미 영상 공모전",
            field = "기획 아이디어 예술 영상",
            period = "2022. 07. 01 ~ 2022. 07. 31",
            organization = listOf("라쿤"),
            reward = "최대 100만원",
            detail = """
                ‘피란수도 부산유산’을 아시나요?
                한국전쟁 당시 함락되었던 서울을 대신하여
                1023일간 임시수도의 역할로 우리를 지켜낸
                피란수도 부산에서 중요한 기능을 했던 곳인데요,
                당시 역사와 세월을 고스란히 담은 유산이 오늘까지 부산 곳곳에 남아있답니다.
                현재 피란수도 부산유산의 유네스코 세계유산으로 등재하기 위해서는
                시민분들의 많은 관심과 응원이 필요합니다!
                다양한 방법으로 피란수도 부산유산 유네스코 세계유산 등재를 응원해주세요!
                
                댄스, 영상메시지, 그림그리기, 연기 등 모든 영상 방식 대환영!
                    
                대상 (1명) : 100만원 상금 + 부산광역시장상
               ️우수상 (1명) : 50만원 상금
               ️장려상 (1명) : 30만원 상금
               ️특별상 (10명) : 치킨 기프티콘 선물!
                    """.trimIndent()
        )

        /**여긴 대외활동입니다**/

        val activity = Contest(
            contestId = 4L,
            title = "RUN&ON:리틀히어로",
            field = "문화 플로깅 국제교류 청소년",
            period = "2022. 06. 29 ~ 2022. 07. 08",
            organization = listOf("구립잠실청소년센터"),
            reward = "수료증 및 인증서, 봉사활동시간",
            detail = """
                함께 달리는 청소년 환경&문화 교류 프로그램
                RUN & ON : Little HERO(1기)참가자를 모집합니다.
                서로 다른 나라에서 함께 환경에 대해 고민하고 비대면으로 만나 논의하며
                플로깅 등의 환경 보호 실천활동을 함께 하며 문화도 교류해요!
                
                - 세계 공통의 문제인 기후변화 위기 회복을 위한 각 국 청소년들이 함께 하는 환경문제 토의
                - 업사이클링, 플로깅 활동을 통하여 지속가능한 환경보호 활동을 함께 찾아보기
                - 자신의 생활환경을 중심으로 각국의 문화를 소개하며 서로 비대면으로 교류
                    """.trimIndent(),
            1
        )
        val activity1 = Contest(
            contestId = 5L,
            title = "2022 독서동아리 공간 발굴 활동가 모집",
            field = "문화 역사 사회공헌 교류",
            period = "2022. 06. 20 ~ 2022. 07. 03",
            organization = listOf("책읽는사회문화재단"),
            reward = "활동비",
            detail = """
                독서동아리들의 큰 고민 중의 하나가 모여서 함께 읽고 이야기를 나누기 좋은 지역 공간 찾는 일입니다.
                동아리 맞춤형 공간을 찾아 나설 공간 발굴 활동가를 모집합니다!
                    """.trimIndent(),
            1
        )
        val activity2 = Contest(
            contestId = 6L,
            title = "대학생 입시 컨설턴트",
            field = "교육 멘토링 수업",
            period = "2022. 06. 29 ~ 2022. 12. 31",
            organization = listOf("소다란"),
            reward = "활동비, 수료증 및 인증서",
            detail = """
                안녕하세요, 온라인 입시 플랫폼 소다란입니다. :)
                소다란은 현재 약 650명의 컨설턴트와 함께 전국 고등학생에게 온라인으로 생기부 컨설팅, 자소서 컨설팅, 자소서 첨삭 서비스를 제공 중이며, 
                저희와 함께 할 대학생 컨설턴트를 모집합니다.
                
                컨설팅 1회 당 일정 금액의 원고료를 제공해 드리고 있으며 소다란 내 모든 활동 내역은 활동증명서 발급이 가능합니다.
                - 모집 대상: 학생부종합전형으로 합격한 재학생 누구나 (휴학생 가능, 비교적 최근에 입시를 경험하신 22, 21학번 재학생 분들의 많은 참여 부탁 드립니다.)
                
                * 지원 시 학생증 or 합격증으로 인증이 필요합니다.
                * 컨설턴트 최초 1회 등록 시, 별도로 컨설턴트 활동 중단 의사를 밝히지 않는 경우 계속해서 컨설팅 참여가 가능합니다.
                
                - 신청 방법: http://www.sodaran.com → 소다란 입시컨설팅 → 컨설턴트 지원
                - 문의 사항: http://pf.kakao.com/_vWaCxb (카카오톡 채널: 소다란_대학생용)
                
                많은 지원 부탁드립니다.
                    """.trimIndent(),
            1
        )
        val activity3 = Contest(
            contestId = 7L,
            title = " 2022 군산새만금컨벤션센터 온라인 서포터즈",
            field = "행사 페스티벌 콘텐츠",
            period = "2022. 07. 14 ~ 2022. 11. 30",
            organization = listOf("군산새만금컨벤션센터"),
            reward = "활동비, 수료증 및 인증서",
            detail = """
                군산새만금컨벤션센터(GSCO)에서 온라인 서포터즈를 모집합니다.
                · 활동내용
                - 블로그, 카드뉴스 등 콘텐츠 제작 후 개인 SNS 및 GSCO 공식계정 업로드(월 2건이상)
                - 오프라인 콘텐츠 회의(월1회)
                
                · 활동혜택
                - 서포터즈 명찰지급(GSCO 주요행사 출입증) 
                    *타주최행사에 따라 입장이 불가할 수 있음
                - 소정의 활동비(10만원) 지급 및 GSCO 현장취재 지원
                - 활동 종료 후 수료증 전달 및 우수서포터즈 시상
                    ※월 2건이상의 콘텐츠 제작 및 업로드 미완료시 활동비 지급 불가
                    """.trimIndent(),
            1
        )

        val allList = listOf<Contest>(
            contest,
            contest1,
            contest2,
            contest3,
            activity,
            activity1,
            activity2,
            activity3
        )

        val projectList = listOf<Contest>(
            activity,
            activity1,
            activity2,
            activity3
        )

        val contestList = listOf<Contest>(
            contest,
            contest1,
            contest2,
            contest3,
        )
    }
}