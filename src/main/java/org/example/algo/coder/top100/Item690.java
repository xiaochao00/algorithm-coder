package org.example.algo.coder.top100;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * 690
 *
 * @author shichao
 * @since 1.0.0
 * 2021/5/4 22:31
 */
public class Item690 {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.id = 1;
        e1.subordinates = Arrays.asList(2, 3);
        e1.importance = 15;

        Employee e2 = new Employee();
        e2.id = 2;
        e2.subordinates = Collections.emptyList();
        e2.importance = 10;

        Employee e3 = new Employee();
        e3.id = 3;
        e3.subordinates = Collections.emptyList();
        e3.importance = 10;

        int sum = getImportance(Arrays.asList(e1, e2, e3), 1);
        System.out.println(sum);
        sum = getImportance2(Arrays.asList(e1, e2, e3), 1);
        System.out.println(sum);
    }

    static class Employee {
        Employee() {

        }

        public int id;
        public int importance;
        public List<Integer> subordinates;
    }


    public static int getImportance2(List<Employee> employees, int id) {
        Employee curEmployee = null;
        for (Employee e : employees) {
            if (id == e.id) {
                curEmployee = e;
                break;
            }
        }
        assert curEmployee != null;
        int sum = curEmployee.importance;
        for (Integer sub : curEmployee.subordinates) {
            sum += getImportance2(employees, sub);
        }
        return sum;
    }

    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> idMap = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e));
        Queue<Employee> queue = new LinkedBlockingQueue<>();
        int sum = 0;
        queue.add(idMap.get(id));
        while (!queue.isEmpty()) {
            Employee curEmployee = queue.poll();
            sum += curEmployee.importance;
            for (int subId : curEmployee.subordinates) {
                queue.add(idMap.get(subId));
            }
        }
        return sum;
    }
}
