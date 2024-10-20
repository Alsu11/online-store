package simbirsoft.question.questionserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirsoft.question.questionserver.domain.Product;
import simbirsoft.question.questionserver.domain.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByOwner(User owner);
}