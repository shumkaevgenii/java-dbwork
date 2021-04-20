package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.*;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "insert into trainee (id, firstName, lastName, rating) values (null, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                trainee.setId(id);
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "update trainee set firstName = ?, lastName = ?, rating = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());
            preparedStatement.setInt(4, trainee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from trainee where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, traineeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int rating = resultSet.getInt("rating");
                return new Trainee(id, firstName, lastName, rating);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from trainee where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, traineeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int rating = resultSet.getInt(4);
                return new Trainee(id, firstName, lastName, rating);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from trainee";
        List<Trainee> trainees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int rating = resultSet.getInt("rating");

                trainees.add(new Trainee(id, firstName, lastName, rating));
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return trainees;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from trainee";
        List<Trainee> trainees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int rating = resultSet.getInt(4);

                trainees.add(new Trainee(id, firstName, lastName, rating));
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return trainees;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "delete from trainee where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trainee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void deleteTrainees() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "delete from trainee";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "insert into subject (id, name) values (null, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subject.getName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                subject.setId(id);
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from subject where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Subject(id, name);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from subject where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, subjectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                return new Subject(id, name);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public static void deleteSubjects() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "delete from subject";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "insert into school (id, name, year) values (null, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, school.getName());
            preparedStatement.setInt(2, school.getYear());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                school.setId(id);
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from school where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, schoolId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                return new School(id, name, year);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select * from school where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, schoolId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int year = resultSet.getInt(3);
                return new School(id, name, year);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public static void deleteSchools() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "delete from school";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "insert into `group` (id, name, room, school_id) values (null, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getRoom());
            preparedStatement.setInt(3, school.getId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                group.setId(id);
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select school.id, school.name, school.year, `group`.id, `group`.name, `group`.room from school join `group` on `group`.school_id = ?";
        List<Group> groups = new ArrayList<>();
        School school = new School();


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int school_id = resultSet.getInt(1);
                String school_name = resultSet.getString(2);
                int school_year = resultSet.getInt(3);
                school = new School(school_id, school_name, school_year);
            }

            do {
                int group_id = resultSet.getInt(4);
                String group_name = resultSet.getString(5);
                String group_room = resultSet.getString(6);
                groups.add(new Group(group_id, group_name, group_room));
            } while (resultSet.next());

            school.setGroups(groups);
            return school;

        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String query = "select school.id, school.name, school.year, `group`.id, `group`.name, `group`.room from school join `group` on `group`.school_id = school.id";

        List<Group> groups = new ArrayList<>();
        List<School> schools = new ArrayList<>();
        Set<Integer> schoolIds = new HashSet<>();


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int school_id = resultSet.getInt(1);
                String school_name = resultSet.getString(2);
                int school_year = resultSet.getInt(3);
                int group_id = resultSet.getInt(4);
                String group_name = resultSet.getString(5);
                String group_room = resultSet.getString(6);

                groups.add(new Group(group_id, group_name, group_room));

                if (schoolIds.contains(school_id)) {
                    School school = new School(school_id, school_name, school_year);
                    school.setGroups(groups);
                    schools.add(school);

                    groups = new ArrayList<>();
                }

                schoolIds.add(school_id);
            }
            return schools;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

}
