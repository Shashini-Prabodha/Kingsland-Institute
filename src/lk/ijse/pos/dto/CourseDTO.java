package lk.ijse.pos.dto;

public class CourseDTO {
        String code;
        String courseName;
        String courseType;
        String duration;

        public CourseDTO(String code, String courseName, String courseType, String duration) {
            this.code = code;
            this.courseName = courseName;
            this.courseType = courseType;
            this.duration = duration;
        }

        public CourseDTO() {
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "code='" + code + '\'' +
                    ", courseName='" + courseName + '\'' +
                    ", courseType='" + courseType + '\'' +
                    ", duration='" + duration + '\'' +
                    '}';
        }
}
