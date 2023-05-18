package com.company.detailsservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostDetailsRepository extends JpaRepository<PostDetail, Integer> {
    @Query("select p from PostDetail p where p.postId = ?1")
    Optional<PostDetail> findByPostId(Integer postId);
}
