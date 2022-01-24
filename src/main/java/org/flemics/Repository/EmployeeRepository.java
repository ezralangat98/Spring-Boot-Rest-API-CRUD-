package org.flemics.Repository;

import org.flemics.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository enable CRUD DB Operations
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
