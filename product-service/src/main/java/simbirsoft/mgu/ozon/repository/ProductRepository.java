package simbirsoft.mgu.ozon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirsoft.mgu.ozon.domain.Product;
import simbirsoft.mgu.ozon.domain.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getProductsByOwner(User user);
}
