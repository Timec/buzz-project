package com.timec.buzz.web.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.timec.buzz.web.domain.Posts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {

	@Autowired
	private PostsRepository postsRepository;

	@AfterEach
	public void cleanup() {
		postsRepository.deleteAll();
	}

	@Test
	public void 게시글저장_불러오기() {
		//given
		String title = "테스트 게시글";
		String content = "테스트 본문";

		postsRepository.save(Posts.builder().title(title).content(content).author("test author").build());

		//when
		List<Posts> postsList = postsRepository.findAll();

		//then
		Posts posts = postsList.get(0);
		Assertions.assertEquals(title, posts.getTitle());
		Assertions.assertEquals(content, posts.getContent());
	}

	@Test
	public void BaseTimeEntity_등록() {
		//given
		LocalDateTime now = LocalDateTime.of(2022,02, 24, 0, 0, 0);
		postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

		//when
		List<Posts> postsList = postsRepository.findAll();

		//then
		Posts posts = postsList.get(0);

		System.out.println(">>>>>>> createDate" + posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);
	}
}
