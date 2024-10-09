package simbirsoft.mgu.ozon.fileservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import simbirsoft.mgu.ozon.fileservice.domain.FileEntity;

public interface FileRepository extends MongoRepository<FileEntity, String> {
}
