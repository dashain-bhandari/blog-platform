package in.dash.blog_platform.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dash.blog_platform.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,UUID> {



}
