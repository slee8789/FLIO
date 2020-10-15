package com.fund.flio.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Search {

    private String targetUrl;
    private String baseUrl;
    private String title;
    private String content;
    private String regDate;
    private String imageUrl;


//     "targetUrl": "https://post.naver.com/viewer/postView.nhn?volumeNo=27643748&memberNo=8871375&searchRank=78",
//             "baseUrl": "naver.com",
//             "title": "[사심리뷰] 컨스텔레이션 인티 1.0 \n최적화 파워 케이블을 찾아라 -1 ",
//             "content": "‘블라인드 <HS>테스트</HE>’ 방식이다. 하이파이 오디오 분야에서 꽤나 오래된 담론 중 하나지만 최근 들어 이런 <HS>테스트</HE> 방식이 없었던 것을 감안하면 독자들에게 큰 도움이 될 수 있을지도 모르겠다. 한편 리뷰어 입장에선 정말 흥미로운 시간이 되기도 했다.  이번 <HS>테스트</HE>에 동원한 케이블은",
//             "keyword": "오디오 인터페이스 dac",
//             "query": null
}
