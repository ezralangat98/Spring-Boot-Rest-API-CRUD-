package org.flemics.Controller;

import org.flemics.Exception.ResourceNotFoundException;
import org.flemics.Model.Employee;
import org.flemics.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //Used for making Rest APIs - Combination of Controller and ResponseBody - allows us to return String data rather than a full view itself
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    /** READ or LIST*/
    @GetMapping("/employees") //@GetMapping in a shortcut for @RequestMapping with http get request
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    /** READ / Find By ID */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee>  getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    /** CREATE or SAVE  */
    /**	@RequestBody Uses http message convertors to convert json message into java object */
    @PostMapping("/employees") //@PostMapping in a shortcut for @RequestMapping with http post request
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    /** UPDATE */
    /** @PathVariable is used in Binding url value to method parameter value */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

//    /** DELETE */
//    @DeleteMapping("/employees/{id}")
//    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
//            throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }

    /** DELETE V2*/
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee1(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.deleteById(employeeId);

        return ResponseEntity.ok().build();
    }
}
