package com.timec.buzz.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timec.buzz.web.domain.Posts;
import com.timec.buzz.web.domain.user.Role;
import com.timec.buzz.web.dto.PostsSaveRequestDto;
import com.timec.buzz.web.dto.PostsUpdateRequestDto;
import com.timec.buzz.web.repository.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostsRepository postsRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@BeforeEach
	public void init() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@AfterEach
	public void tearDown() {
		postsRepository.deleteAll();
	}

	@Test
	@WithMockUser(roles = "USER")
	public void Posts_등록() throws Exception {
		//given
		String title = "title";
		String content = "content";
		PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
				.title(title)
				.content(content)
				.author("test author")
				.build();
		String url = "http://localhost:" + port + "/api/v1/posts";

		//when
//		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
		mvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestDto)))
				.andExpect(status().isOk());

		//then
//		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);
	}

	@Test
	@WithMockUser(roles = "USER")
	public void Posts_수정() throws Exception {
		//given
		Posts savedPosts = postsRepository.save(Posts.builder()
						.title("title")
						.content("content")
						.author("author").build());

		Long updateId = savedPosts.getId();
		String expectedTitle = "title2";
		String expectedContent = "content2";

		PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
				.title(expectedTitle).content(expectedContent).build();

		String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

		HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

		//when
//		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
		mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestDto)))
				.andExpect(status().isOk());

		//then
//		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

//		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
		assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

	}

}
