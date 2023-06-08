package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
  ConcurrentHashMap<Long,Post> allPosts;
  AtomicLong counter = new AtomicLong();
  public PostRepository(){

    this.allPosts=new ConcurrentHashMap<>();
  }
  public Collection<Post> all() {
    return allPosts.values();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(allPosts.get(id));
  }

  public Post save(Post post) {
    if(post.getId()==0){
      long id = counter.incrementAndGet();
      post.setId(id);
      allPosts.put(id,post);
    } else if (post.getId() != 0) {
      Long currentId = post.getId();
      allPosts.put(currentId, post);
    }
    return post;
  }

  public void removeById(long id) {
    allPosts.remove(id);
  }
}
