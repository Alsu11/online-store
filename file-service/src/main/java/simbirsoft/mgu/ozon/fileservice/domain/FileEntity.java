package simbirsoft.mgu.ozon.fileservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "files")
public class FileEntity {
    private String _id;
    private String fileName;
    private LocalDateTime uploadDate;
    private Binary bytes;
}
