package com.example.courselistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.courselistapp.ui.theme.CourseListTheme
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

class MainActivity : ComponentActivity() {
    private val sampleCourses = listOf(
        Course(
            title = "Introduction to Computer Science",
            code = "CS101",
            creditHours = 3,
            description = "This introductory course covers the fundamental principles of computer science, including programming basics, problem-solving techniques, and the impact of computing on society. Students will gain hands-on experience with programming languages and develop critical thinking skills essential for future studies in technology.",
            prerequisites = "This course is designed for beginners and requires no prior knowledge of computer science or programming."
        ),
        Course(
            title = "Data Structures and Algorithms",
            code = "CS201",
            creditHours = 4,
            description = "This course delves into the essential data structures (like arrays, linked lists, trees, and graphs) and algorithms used to manipulate them. Students will learn how to analyze algorithm efficiency and apply these concepts to solve complex computational problems effectively.",
            prerequisites = " Students must have a foundational understanding of programming concepts and basic computer science principles."
        ),
        Course(
            title = "Operating Systems",
            code = "CS301",
            creditHours = 3,
            description = "This course provides an in-depth exploration of modern operating system concepts, including process management, memory management, file systems, and system security. Students will understand how operating systems function and their role in managing hardware and software resources.",
            prerequisites = "A solid grasp of data structures and algorithms is essential, as this course builds on those concepts to explore operating system functionality."
        ),
        Course(
            title = "Database Systems",
            code = "CS302",
            creditHours = 3,
            description = "This course introduces students to the principles of database design, modeling, and querying using SQL. Topics include relational database management systems, normalization, and transaction management, equipping students with the skills to design and manage efficient databases.",
            prerequisites = "Students should have a background in data structures and algorithms to effectively understand database design and querying techniques."
        ),
        Course(
            title = "Software Engineering",
            code = "CS401",
            creditHours = 3,
            description = "This course covers the software development life cycle, including methodologies, design patterns, testing, and maintenance. Students will learn best practices for software development, project management, and teamwork, preparing them for real-world software engineering",
            prerequisites = " A prerequisite understanding of data structures and algorithms is necessary, along with familiarity with programming concepts, to tackle software development methodologies and practices."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseListScreen(courses = sampleCourses)
                }
            }
        }
    }
}

@Composable
fun CourseListScreen(courses: List<Course>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        items(courses, key = { it.code }) { course ->
            CourseCard(course = course)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(course: Course) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expanded = !expanded },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize()
        ) {
// Essential info row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Code: ${course.code}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = "${course.creditHours} Credit${if (course.creditHours > 1) "s" else ""}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.alignByBaseline()
                )
            }
// Expanded info shown only when expanded == true
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Description:",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Prerequisites:",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = course.prerequisites,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

// Preview for light theme portrait mode
@Preview(
    name = "Light Mode Portrait",
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun CourseListScreenLightPreview() {
    CourseListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CourseListScreen(
                courses = listOf(
                    Course(
                        title = "Introduction to Computer Science",
                        code = "CS101",
                        creditHours = 3,
                        description = "This introductory course covers the fundamental principles of computer science, including programming basics, problem-solving techniques, and the impact of computing on society. Students will gain hands-on experience with programming languages and develop critical thinking skills essential for future studies in technology.",
                        prerequisites = "This course is designed for beginners and requires no prior knowledge of computer science or programming."
                    ),
                    Course(
                        title = "Data Structures and Algorithms",
                        code = "CS201",
                        creditHours = 4,
                        description = "This course delves into the essential data structures (like arrays, linked lists, trees, and graphs) and algorithms used to manipulate them. Students will learn how to analyze algorithm efficiency and apply these concepts to solve complex computational problems effectively.",
                        prerequisites = " Students must have a foundational understanding of programming concepts and basic computer science principles."
                    ),
                    Course(
                        title = "Operating Systems",
                        code = "CS301",
                        creditHours = 3,
                        description = "This course provides an in-depth exploration of modern operating system concepts, including process management, memory management, file systems, and system security. Students will understand how operating systems function and their role in managing hardware and software resources.",
                        prerequisites = "A solid grasp of data structures and algorithms is essential, as this course builds on those concepts to explore operating system functionality."
                    ),
                    Course(
                        title = "Database Systems",
                        code = "CS302",
                        creditHours = 3,
                        description = "This course introduces students to the principles of database design, modeling, and querying using SQL. Topics include relational database management systems, normalization, and transaction management, equipping students with the skills to design and manage efficient databases.",
                        prerequisites = "Students should have a background in data structures and algorithms to effectively understand database design and querying techniques."
                    ),
                    Course(
                        title = "Software Engineering",
                        code = "CS401",
                        creditHours = 3,
                        description = "This course covers the software development life cycle, including methodologies, design patterns, testing, and maintenance. Students will learn best practices for software development, project management, and teamwork, preparing them for real-world software engineering",
                        prerequisites = " A prerequisite understanding of data structures and algorithms is necessary, along with familiarity with programming concepts, to tackle software development methodologies and practices."
                    )
                )
            )
        }
    }
}

@Preview(
    name = "Dark Mode Portrait",
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun CourseListScreenDarkPreview() {
    CourseListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CourseListScreen(
                courses = listOf(
                    Course(
                        title = "Introduction to Computer Science",
                        code = "CS101",
                        creditHours = 3,
                        description = "This introductory course covers the fundamental principles of computer science, including programming basics, problem-solving techniques, and the impact of computing on society. Students will gain hands-on experience with programming languages and develop critical thinking skills essential for future studies in technology.",
                        prerequisites = "This course is designed for beginners and requires no prior knowledge of computer science or programming."
                    ),
                    Course(
                        title = "Data Structures and Algorithms",
                        code = "CS201",
                        creditHours = 4,
                        description = "This course delves into the essential data structures (like arrays, linked lists, trees, and graphs) and algorithms used to manipulate them. Students will learn how to analyze algorithm efficiency and apply these concepts to solve complex computational problems effectively.",
                        prerequisites = " Students must have a foundational understanding of programming concepts and basic computer science principles."
                    ),
                    Course(
                        title = "Operating Systems",
                        code = "CS301",
                        creditHours = 3,
                        description = "This course provides an in-depth exploration of modern operating system concepts, including process management, memory management, file systems, and system security. Students will understand how operating systems function and their role in managing hardware and software resources.",
                        prerequisites = "A solid grasp of data structures and algorithms is essential, as this course builds on those concepts to explore operating system functionality."
                    ),
                    Course(
                        title = "Database Systems",
                        code = "CS302",
                        creditHours = 3,
                        description = "This course introduces students to the principles of database design, modeling, and querying using SQL. Topics include relational database management systems, normalization, and transaction management, equipping students with the skills to design and manage efficient databases.",
                        prerequisites = "Students should have a background in data structures and algorithms to effectively understand database design and querying techniques."
                    ),
                    Course(
                        title = "Software Engineering",
                        code = "CS401",
                        creditHours = 3,
                        description = "This course covers the software development life cycle, including methodologies, design patterns, testing, and maintenance. Students will learn best practices for software development, project management, and teamwork, preparing them for real-world software engineering",
                        prerequisites = " A prerequisite understanding of data structures and algorithms is necessary, along with familiarity with programming concepts, to tackle software development methodologies and practices."
                    )
                )
            )
        }
    }
}
// Preview for light theme landscape mode
@Preview(
    name = "Light Mode Landscape",
    showBackground = true,
    widthDp = 640,
    heightDp = 360,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun CourseListScreenLightLandscapePreview() {
    CourseListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CourseListScreen(
                courses = listOf(
                    Course(
                        title = "Introduction to Computer Science",
                        code = "CS101",
                        creditHours = 3,
                        description = "This introductory course covers the fundamental principles of computer science, including programming basics, problem-solving techniques, and the impact of computing on society. Students will gain hands-on experience with programming languages and develop critical thinking skills essential for future studies in technology.",
                        prerequisites = "This course is designed for beginners and requires no prior knowledge of computer science or programming."
                    ),
                    Course(
                        title = "Data Structures and Algorithms",
                        code = "CS201",
                        creditHours = 4,
                        description = "This course delves into the essential data structures (like arrays, linked lists, trees, and graphs) and algorithms used to manipulate them. Students will learn how to analyze algorithm efficiency and apply these concepts to solve complex computational problems effectively.",
                        prerequisites = " Students must have a foundational understanding of programming concepts and basic computer science principles."
                    ),
                    Course(
                        title = "Operating Systems",
                        code = "CS301",
                        creditHours = 3,
                        description = "This course provides an in-depth exploration of modern operating system concepts, including process management, memory management, file systems, and system security. Students will understand how operating systems function and their role in managing hardware and software resources.",
                        prerequisites = "A solid grasp of data structures and algorithms is essential, as this course builds on those concepts to explore operating system functionality."
                    ),
                    Course(
                        title = "Database Systems",
                        code = "CS302",
                        creditHours = 3,
                        description = "This course introduces students to the principles of database design, modeling, and querying using SQL. Topics include relational database management systems, normalization, and transaction management, equipping students with the skills to design and manage efficient databases.",
                        prerequisites = "Students should have a background in data structures and algorithms to effectively understand database design and querying techniques."
                    ),
                    Course(
                        title = "Software Engineering",
                        code = "CS401",
                        creditHours = 3,
                        description = "This course covers the software development life cycle, including methodologies, design patterns, testing, and maintenance. Students will learn best practices for software development, project management, and teamwork, preparing them for real-world software engineering",
                        prerequisites = " A prerequisite understanding of data structures and algorithms is necessary, along with familiarity with programming concepts, to tackle software development methodologies and practices."
                    )
                )
            )
        }
    }
}
// Preview for dark theme landscape mode
@Preview(
    name = "Dark Mode Landscape",
    showBackground =true,
    widthDp = 640,
    heightDp = 360,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun CourseListScreenDarkLandscapePreview() {
    CourseListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CourseListScreen(
                courses = listOf(
                    Course(
                        title = "Introduction to Computer Science",
                        code = "CS101",
                        creditHours = 3,
                        description = "This introductory course covers the fundamental principles of computer science, including programming basics, problem-solving techniques, and the impact of computing on society. Students will gain hands-on experience with programming languages and develop critical thinking skills essential for future studies in technology.",
                        prerequisites = "This course is designed for beginners and requires no prior knowledge of computer science or programming."
                    ),
                    Course(
                        title = "Data Structures and Algorithms",
                        code = "CS201",
                        creditHours = 4,
                        description = "This course delves into the essential data structures (like arrays, linked lists, trees, and graphs) and algorithms used to manipulate them. Students will learn how to analyze algorithm efficiency and apply these concepts to solve complex computational problems effectively.",
                        prerequisites = " Students must have a foundational understanding of programming concepts and basic computer science principles."
                    ),
                    Course(
                        title = "Operating Systems",
                        code = "CS301",
                        creditHours = 3,
                        description = "This course provides an in-depth exploration of modern operating system concepts, including process management, memory management, file systems, and system security. Students will understand how operating systems function and their role in managing hardware and software resources.",
                        prerequisites = "A solid grasp of data structures and algorithms is essential, as this course builds on those concepts to explore operating system functionality."
                    ),
                    Course(
                        title = "Database Systems",
                        code = "CS302",
                        creditHours = 3,
                        description = "This course introduces students to the principles of database design, modeling, and querying using SQL. Topics include relational database management systems, normalization, and transaction management, equipping students with the skills to design and manage efficient databases.",
                        prerequisites = "Students should have a background in data structures and algorithms to effectively understand database design and querying techniques."
                    ),
                    Course(
                        title = "Software Engineering",
                        code = "CS401",
                        creditHours = 3,
                        description = "This course covers the software development life cycle, including methodologies, design patterns, testing, and maintenance. Students will learn best practices for software development, project management, and teamwork, preparing them for real-world software engineering",
                        prerequisites = " A prerequisite understanding of data structures and algorithms is necessary, along with familiarity with programming concepts, to tackle software development methodologies and practices."
                    )
                )
            )
        }
    }
}
