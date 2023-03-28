package com.example.blogenspringmvc.service;

import com.example.blogenspringmvc.dao.CategoryDao;
import com.example.blogenspringmvc.dao.PostDao;
import com.example.blogenspringmvc.dao.UserDao;
import com.example.blogenspringmvc.entity.Category;
import com.example.blogenspringmvc.entity.Post;
import com.example.blogenspringmvc.entity.Users;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private CategoryDao categoryDao;
    private PostDao postDao;
    private UserDao userDao;

    public PostService(CategoryDao categoryDao, PostDao postDao, UserDao userDao) {
        this.categoryDao = categoryDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public List<Post> findAllPost(){
        return postDao.findAll();
    }
    @Transactional
    public void savePost(Post post){
        Category category=categoryDao.findById(post.getCategory().getId())
                .orElseThrow(EntityNotFoundException::new);

        Users users=userDao.findById(post.getUsers().getId())
                .orElseThrow(EntityNotFoundException::new);

        category.addPost(post);
        users.addPost(post);
        postDao.save(post);
    }
}
