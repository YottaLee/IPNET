package com.ipnet.dao.communitydao;

import com.ipnet.entity.communityentity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name = "post")
public interface PostDao extends JpaRepository<Post,String>{
    @Query("select p from Post p where p.author=:author")
    ArrayList<Post> getPostByAuthor(@Param("author") String author);

    @Query(value = "select p from Post p where p.post_name like %?1%")
    ArrayList<Post> searchArticle(String keywords);

}
