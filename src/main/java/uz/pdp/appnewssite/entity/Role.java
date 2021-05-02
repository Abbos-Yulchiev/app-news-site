package uz.pdp.appnewssite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appnewssite.entity.enums.Permission;
import uz.pdp.appnewssite.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Permission> permissionList;

    @Column(columnDefinition = "text", length = 500)
    private String description;
}
