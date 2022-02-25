package com.timec.buzz.web.service;

import java.util.List;
import java.util.stream.Collectors;

import com.timec.buzz.web.domain.Posts;
import com.timec.buzz.web.dto.PostsListResponseDto;
import com.timec.buzz.web.dto.PostsResponseDto;
import com.timec.buzz.web.dto.PostsSaveRequestDto;
import com.timec.buzz.web.dto.PostsUpdateRequestDto;
import com.timec.buzz.web.repository.PostsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto postsSaveRequestDto) {
		return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
		return new PostsResponseDto(entity);
	}

	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));
		postsRepository.delete(posts);
	}
}
