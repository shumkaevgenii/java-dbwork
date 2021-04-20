package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupMapper {

    @Insert("INSERT INTO `group` (name, room, school_id) VALUES" +
            "(#{group.name}, #{group.room}, #{school.id})")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    Integer insert(@Param("school") School school, @Param("group") Group group);

    @Update("UPDATE `group` SET name = #{group.name}, room = #{group.room}" +
            "WHERE id = #{group.id}")
    void update(@Param("group") Group group);

    @Select("SELECT * FROM `group`")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getTraineesByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getSubjectsByGroup", fetchType = FetchType.LAZY))
    })
    List<Group> getAll();


    @Select("SELECT * FROM `group` WHERE school_id = #{school.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getTraineesByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getSubjectsByGroup", fetchType = FetchType.LAZY))
    })
    List<Group> getGroupsBySchool(@Param("school") School school);

    @Delete("DELETE FROM `group` WHERE id = #{group.id}")
    void delete(@Param("group") Group group);

    @Update("UPDATE trainee SET group_id = #{group.id} WHERE id = #{trainee.id}")
    void moveTraineeToGroup(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Update("UPDATE trainee SET group_id = NULL WHERE id = #{trainee.id}")
    void deleteTraineeFromGroup( @Param("trainee") Trainee trainee);

    @Insert("INSERT subject_group (subject_id, group_id) VALUES " +
            "(#{subject.id}, #{group.id})")
    void addSubjectToGroup(@Param("group") Group group, @Param("subject") Subject subject);
}
