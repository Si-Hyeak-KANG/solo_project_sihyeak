package api.v1.domain.member.controller;

import api.v1.domain.companylocation.CompanyLocation;
import api.v1.domain.companytype.CompanyType;
import api.v1.domain.member.entity.Gender;
import api.v1.domain.member.entity.Member;
import api.v1.domain.member.mapper.MemberMapper;
import api.v1.domain.member.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import api.v1.domain.member.memberdto.MemberResponseDto;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void getMemberTest() throws Exception {
        //given
        MemberResponseDto response = new MemberResponseDto(
                1L,
                "zlcls456@gmail.com",
                "username",
                28,
                Gender.MAN,
                "회사이름",
                new CompanyType("서비스업","001"),
                new CompanyLocation("서울","001")
        );
        //when
        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(response);

        ResultActions actions = mockMvc.perform(
                get("/v1/members/{member-id}", 1)
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(response.getUsername()))
                .andDo(document(
                   "get-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.username").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data.age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("data.gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data.companyName").type(JsonFieldType.STRING).description("사업명"),
                                        fieldWithPath("data.companyType").type(JsonFieldType.STRING).description("업종 코드"),
                                        fieldWithPath("data.companyLocation").type(JsonFieldType.STRING).description("사업 지역 코드")
                                )
                        )
                ));

    }

    @Test
    public void getMembersTest() throws Exception {
        //given
        List<MemberResponseDto> responses = List.of(
                new MemberResponseDto(1L,
                        "zlcls456@gmail.com",
                        "username",
                        28,
                        Gender.MAN,
                        "회사이름",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("서울","001")),
                new MemberResponseDto(2L,
                        "test@gmail.com",
                        "김코딩",
                        20,
                        Gender.WOMAN,
                        "코드스테이츠",
                        new CompanyType("제조업","002"),
                        new CompanyLocation("경기","002")),
                new MemberResponseDto(3L,
                        "ofcourse@gmail.com",
                        "물론이",
                        23,
                        Gender.WOMAN,
                        "카카오",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("제주","003"))
        );

        Page<Member> page = new PageImpl(responses);
        //when
        given(memberService.findMembers(Mockito.anyInt(),Mockito.anyInt())).willReturn(page);
        given(mapper.membersToMemberResponsesDto(Mockito.anyList())).willReturn(responses);

        ResultActions ac = mockMvc.perform(
                get("/v1/members")
                        .param("page", "1")
                        .param("size", "10")
        );

        //then
        ac.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value(responses.get(0).getUsername()))
                .andDo(document(
                   "get-members",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("Page 번호"),
                                        parameterWithName("size").description("Page Size")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].username").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("data[].gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("사업명"),
                                        fieldWithPath("data[].companyType").type(JsonFieldType.STRING).description("업종 코드"),
                                        fieldWithPath("data[].companyLocation").type(JsonFieldType.STRING).description("사업 지역 코드"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보").optional(),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호").optional(),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈").optional(),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 건 수").optional(),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수").optional()
                                )
                        )
                ));

    }

    @Test
    @DisplayName("업종, 지역으로 조회할 시")
    public void getMembersByConditionTest() throws Exception {
        //given
        List<MemberResponseDto> responses = List.of(
                new MemberResponseDto(1L,
                        "zlcls456@gmail.com",
                        "username",
                        28,
                        Gender.MAN,
                        "회사이름",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("서울","001")),
                new MemberResponseDto(4L,
                        "james@gmail.com",
                        "제임스",
                        38,
                        Gender.MAN,
                        "카카오",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("서울","001"))
        );

        //when
        given(memberService.findMembersByCondition(Mockito.anyString(),Mockito.anyString())).willReturn(List.of(new Member()));
        given(mapper.membersToMemberResponsesDto(Mockito.anyList())).willReturn(responses);

        ResultActions ac = mockMvc.perform(
                get("/v1/members/search")
                        .param("type", "001")
                        .param("location","001")
        );

        //then
        ac.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value(responses.get(0).getUsername()))
                .andExpect(jsonPath("$.data[1].username").value(responses.get(1).getUsername()))
                .andDo(document(
                        "get-members-byCondition-all",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("type").description("업종 코드").optional(),
                                        parameterWithName("location").description("지역 코드").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].username").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("data[].gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("사업명"),
                                        fieldWithPath("data[].companyType").type(JsonFieldType.STRING).description("업종 코드"),
                                        fieldWithPath("data[].companyLocation").type(JsonFieldType.STRING).description("사업 지역 코드")
                                )
                        )
                ));

    }

    @Test
    @DisplayName("특정 지역으로 조회할 시")
    public void getMembersByConditionTest2() throws Exception {
        //given
        List<MemberResponseDto> responses = List.of(
                new MemberResponseDto(3L,
                        "ofcourse@gmail.com",
                        "물론이",
                        23,
                        Gender.WOMAN,
                        "인프런",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("제주","003"))
        );

        //when
        given(memberService.findMembersByCondition(Mockito.anyString(),Mockito.anyString())).willReturn(List.of(new Member()));
        given(mapper.membersToMemberResponsesDto(Mockito.anyList())).willReturn(responses);

        ResultActions ac = mockMvc.perform(
                get("/v1/members/search")
                        .param("location", "003")
        );

        //then
        ac.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value(responses.get(0).getUsername()))
                .andDo(document(
                        "get-members-byCondition-location",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("type").description("업종 코드").optional(),
                                        parameterWithName("location").description("지역 코드").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].username").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("data[].gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("사업명"),
                                        fieldWithPath("data[].companyType").type(JsonFieldType.STRING).description("업종 코드"),
                                        fieldWithPath("data[].companyLocation").type(JsonFieldType.STRING).description("사업 지역 코드")
                                )
                        )
                ));

    }

    @Test
    @DisplayName("특정 업종으로 조회할 시")
    public void getMembersByConditionTest3() throws Exception {
        //given
        List<MemberResponseDto> responses = List.of(
                new MemberResponseDto(1L,
                        "zlcls456@gmail.com",
                        "username",
                        28,
                        Gender.MAN,
                        "회사이름",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("서울","001")),
                new MemberResponseDto(3L,
                        "ofcourse@gmail.com",
                        "물론이",
                        23,
                        Gender.WOMAN,
                        "인프런",
                        new CompanyType("서비스업","001"),
                        new CompanyLocation("제주","003"))
        );

        //when
        given(memberService.findMembersByCondition(Mockito.anyString(),Mockito.anyString())).willReturn(List.of(new Member()));
        given(mapper.membersToMemberResponsesDto(Mockito.anyList())).willReturn(responses);

        ResultActions ac = mockMvc.perform(
                get("/v1/members/search")
                        .param("type", "001")
        );

        //then
        ac.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username").value(responses.get(0).getUsername()))
                .andExpect(jsonPath("$.data[1].username").value(responses.get(1).getUsername()))
                .andDo(document(
                        "get-members-byCondition-type",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("type").description("업종 코드").optional(),
                                        parameterWithName("location").description("지역 코드").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].username").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data[].age").type(JsonFieldType.NUMBER).description("나이"),
                                        fieldWithPath("data[].gender").type(JsonFieldType.STRING).description("성별"),
                                        fieldWithPath("data[].companyName").type(JsonFieldType.STRING).description("사업명"),
                                        fieldWithPath("data[].companyType").type(JsonFieldType.STRING).description("업종 코드"),
                                        fieldWithPath("data[].companyLocation").type(JsonFieldType.STRING).description("사업 지역 코드")
                                )
                        )
                ));

    }

}

