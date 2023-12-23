package com.example.goquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goquiz.databinding.ActivityQuestionslistBinding;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionList extends AppCompatActivity {
    private ActivityQuestionslistBinding binding;
    public static ArrayList<Question> questionList;

    private List<Question> filteredList = new ArrayList<>();
    private boolean isFiltered = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionslistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        questionList = questionList();
        QuestionAdapter adapter = new QuestionAdapter(questionList);
        binding.QuestionsListView.setAdapter(adapter);
        binding.QuestionsListView.setOnItemClickListener((parent, view1, i, id) -> {
            Question selected = isFiltered ? filteredList.get(i):questionList.get(i);
            Intent intent = new Intent(QuestionList.this,QuestionDetailed.class);
            intent.putExtra("cauhoi", selected.getQuestionText());
            intent.putExtra("dokho", selected.getDifficultyLevel());
            intent.putExtra("dapan", selected.getCorrectAnswerIndex());
            // Chuyển từ list thành array
            String[] tuychon = selected.getAnswerOptions().toArray(new String[0]);
            intent.putExtra("tuychon", tuychon);
            startActivity(intent);
        });

        String[] categories = getResources().getStringArray(R.array.categories);
        ((MaterialAutoCompleteTextView) binding.from.getEditText()).setSimpleItems(categories);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,categories);
        ((MaterialAutoCompleteTextView) binding.from.getEditText()).setAdapter(adapter1);
        ((MaterialAutoCompleteTextView) binding.from.getEditText()).setOnItemClickListener((parent, view1, position, id) -> {
            String selectedCategory = (String) parent.getItemAtPosition(position);
            if(selectedCategory.equals("Tất cả")){
                isFiltered = false;
                binding.QuestionsListView.setAdapter(adapter);
            }else {
                FilterQuestion(selectedCategory);
            }
        });
    }
    private void FilterQuestion(String category){
        if (filteredList != null) {
            filteredList.clear();
        }
        isFiltered = true;
        for (Question question : questionList){
            if(question.getCategory().equals(category)){
                filteredList.add(question);
            }
        }
        // Cập nhật lại Adapter
        QuestionAdapter adapter2 = new QuestionAdapter(filteredList);
        binding.QuestionsListView.setAdapter(adapter2);
    }
    public static ArrayList<Question> questionList() {
        questionList = new ArrayList<>();
        questionList.add(new Question(1,"Toán học", "Tính giá trị của biểu thức sau: 11 + 56", Arrays.asList("64", "65", "66", "67"), 4, 0));
        questionList.add(new Question(2,"Toán học", "Hình tam giác có bao nhiêu cạnh?", Arrays.asList("3", "4", "5", "6"), 1, 0));
        questionList.add(new Question(3,"Toán học", "Nếu A + B = 76 và A - B = 38 thì A : B = ?", Arrays.asList("3", "4", "5", "6"), 1, 0));
        questionList.add(new Question(4,"Toán học", "Tính giá trị của biểu thức: tan(0)", Arrays.asList("0", "1", "-1", "∞"), 1, 0));
        questionList.add(new Question(5,"Toán học", "Giải phương trình: 9x + 4 = 40", Arrays.asList("x = 4", "x = -4", "x = 3", "x = 2"), 1, 0));
        questionList.add(new Question(6,"Toán học", "Năm ngày trước của ngày sau ngày mai là ngày thứ tư. Hỏi hôm qua là thứ mấy?", Arrays.asList("Thứ Tư", "Thứ Năm", "Thứ Sáu", "Chủ nhật"), 3, 1));
        questionList.add(new Question(7,"Toán học", "Cho tam giác có cạnh a = 6 cm, cạnh b = 8 cm, cạnh c = 10 cm. Tam giác đó là tam giác gì?", Arrays.asList("Tam giác vuông", "Tam giác đều", "Tam giác cân", "Tam giác nhọn"), 1, 1));
        questionList.add(new Question(8,"Toán học", "Gieo đồng tiền 5 lần cân đối và đồng chất. Xác suất để được ít nhất một lần xuất hiện mặt sấp?", Arrays.asList("31/32", "21/32", "11/23", "11/32"), 1, 1));
        questionList.add(new Question(9,"Toán học", "Nếu 5 con gà ấp 5 quả trứng trong 5 ngày là xong. Vậy cần bao nhiêu ngày để 100 con gà ấp xong 100 quả trứng?", Arrays.asList("1 ngày", "3 ngày", "5 ngày", "15 ngày"), 3, 1));
        questionList.add(new Question(10,"Toán học", "Số nào có khác với các số còn lại: 9, 16, 24, 49", Arrays.asList("9", "16", "24", "49"), 3, 1));

        questionList.add(new Question(11,"Vật lý", "Mây ngũ sắc là sự xuất hiện của màu sắc trong một đám mây giống như vết dầu loang ta thấy trên mặt nước.Vậy đám mây ngũ sắc được tạo ra nhờ hiện tượng gì?", Arrays.asList("Nhiễu xạ", "Khúc xạ", "Tán sắc", "Đối lưu"), 1,0));
        questionList.add(new Question(12,"Vật lý","Tai người có thể cảm thụ được những dao động có tần số nằm trong giới hạn nào?", Arrays.asList("10Hz đến 10kHz", "16Hz đến 10kHz", "10Hz đến 20kHz", "16Hz đến 20Khz"), 4, 0));
        questionList.add(new Question(13,"Vật lý","Trong hạt nhân của nguyên tử, hạt nào có điện tích dương?",Arrays.asList("Electron", "Proton", "Photon", "Notron"),2,0));
        questionList.add(new Question(14,"Vật lý","Đơn vị nào đo lường khả năng của một mạch dẫn điện chịu được dòng điện?",Arrays.asList("Volt","Ampe","Ohm","Watt"),3,0));
        questionList.add(new Question(15,"Vật lý","Đơn vị nào đo lường áp suất?",Arrays.asList("Pascal","Newton","Joule","Watt"),1,0));
        questionList.add(new Question(16,"Vật lý","Áp suất khí quyển giảm đi khi độ cao tăng lên. Điều này được mô tả bởi định luật nào?",Arrays.asList("Định luật Pascal","Định luật Archimedes","Định luật Boyle","Định luật Newton"),3,1));
        questionList.add(new Question(17,"Vật lý","Điều gì xảy ra khi nước đun sôi ở nhiệt độ 100 độ C?",Arrays.asList("Chuyển từ chất lỏng thành chất khí","Chuyển từ chất lỏng thành chất rắn","Chuyển từ chất khí thành chất rắn","Chuyển từ chất lỏng thành chất khí "),4,1));
        questionList.add(new Question(18,"Vật lý","Đâu là công thức Ohm?",Arrays.asList("V = IR^2","P = IV","R = V/I","I = V/R"),3,1));
        questionList.add(new Question(19,"Vật lý","Ánh sáng trắng có thể phân chia thành các màu sắc như thế nào?",Arrays.asList("Giảm cường độ","Phản xạ","Quang phổ","Phản chiếu"),3,1));
        questionList.add(new Question(20,"Vật lý","Ai là tác giả của câu nói nổi tiếng: Hãy cho tôi một điểm tựa, tôi sẽ nhấc bổng quả đất lên?",Arrays.asList("Albert Einstein","Archimedes","Nikola Tesla","Thomas Edison"),2,1));

        questionList.add(new Question(21,"Hóa học","Nguyên tố nào chiếm tỷ trọng lớn nhất trong không khí?",Arrays.asList("Nitơ","Oxi","Hidro","Metan"),1,0));
        questionList.add(new Question(22,"Hóa học","Công thức hóa học của nước là gì?",Arrays.asList("H20","HOH","CO2","NH3"),1,0));
        questionList.add(new Question(23,"Hóa học","Loại phản ứng hóa học nào xảy ra khi một nguyên tố kết hợp với một nguyên tố khác để tạo ra hợp chất mới?",Arrays.asList("Phân hủy","Phản ứng oxi hóa","Phản ứng trùng hợp","Phản ứng tổng hợp"),4,0));
        questionList.add(new Question(24,"Hóa học","Công thức hóa học của amoni là gì?",Arrays.asList("NH4","NH3","NO2","NO"),2,0));
        questionList.add(new Question(25,"Hóa học","Nguyên tố nào sao đây là khí hiếm?",Arrays.asList("F","N","Cl","He"),4,0));
        questionList.add(new Question(26,"Hóa học","Nguyên tố nào chiếm tỷ lệ cao nhất trong vỏ trái đất?",Arrays.asList("Sắt","Đồng","Silic","Nhôm"),3,1));
        questionList.add(new Question(27,"Hóa học","Phản ứng hóa học giữa axit và bazơ tạo ra loại chất gì?",Arrays.asList("Muối và nước","Gas","Kết tủa","Nước"),1,1));
        questionList.add(new Question(28,"Hóa học","Cặp chất nào sau đây phản ứng tạo kết tủa trắng?",Arrays.asList("C2H4 và dung dịch KMnO4","Phenol và dung dịch Br2","Phenol và dung dịch HNO3 đặc","CH3NH2 và dung dịch FeCl3"),2,1));
        questionList.add(new Question(29,"Hóa học","Đồng phân của glucozơ là?",Arrays.asList("mantozơ","saccarozơ","glixerol","fructozơ"),4,1));
        questionList.add(new Question(30,"Hóa học","Chất có thể dùng làm mềm nước cứng tạm thời là?",Arrays.asList("NaCl","NaHSO4","Na2CO3","HCl"),3,1));

        questionList.add(new Question(31,"Sinh học","Cây dương xỉ là đại diện tiêu biểu cho nhóm thực vật nào?",Arrays.asList("Hạt trần","Hạt kín","Quyết","Lớp 3 lá mầm"),3,0));
        questionList.add(new Question(32,"Sinh học","Yếu tố nào sau đây không cần thiết cho sự quang hợp ở thực vật?",Arrays.asList("Khí hidro","Nước","Khí cacbonic","Ánh sáng"),1,0));
        questionList.add(new Question(33,"Sinh học","Cá chép có tim mấy ngăn?",Arrays.asList("1","2","3","4"),2,0));
        questionList.add(new Question(34,"Sinh học","Thủy tức thuộc ngành động vật gì?",Arrays.asList("Thân mềm","Ruột khoang","Giun đốt","Động vật nguyên sinh"),2,0));
        questionList.add(new Question(35,"Sinh học","Vai trò của ruột già là gì?",Arrays.asList("Hấp thụ chất dinh dưỡng","Hấp thụ nước và thải phân","Tiết HCl 0.3% cho tiêu hóa","Đào thải enzim"),2,0));
        questionList.add(new Question(36,"Sinh học","Loại tế bào nào tạo ra các tế bào mới thông qua quá trình nguyên phân",Arrays.asList("Tế bào thực vật","Tế bào máu","Tế bào vi khuẩn","Tế bào thực bào"),4,1));
        questionList.add(new Question(37,"Sinh học","Loài nào thuộc họ động vật có xương sống?",Arrays.asList("Nhện","Côn trùng","Động vật lưỡng cư","Động vật thân mềm"),3,1));
        questionList.add(new Question(38,"Sinh học","Loại nào của gen di truyền từ cha hoặc mẹ sang con cái?",Arrays.asList("Gen trội","Gen lặn","Gen đẳng vị","Gen đồng hợp tử"),4,1));
        questionList.add(new Question(39,"Sinh học","Loại cơ bản của cấu trúc gene là gì?",Arrays.asList("RNA","DNA","Protein","Carbohydrate"),2,1));
        questionList.add(new Question(40,"Sinh học","Quá trình nào giúp duy trì sự đa dạng gen trong một quần thể?",Arrays.asList("Di truyền","Đột biến","Chọn lọc tự nhiên","Thích nghi"),3,1));

        questionList.add(new Question(41,"Lịch sử","Năm 938, ai là người lãnh đạo chiến thắng tại Bạch Đằng, chấm dứt sự thống nhất của người Nam và người Bắc?",Arrays.asList("Ngô Quyền","Lê Lợi","Trần Hưng Đạo","Lý Thường Kiệt"),1,0));
        questionList.add(new Question(42,"Lịch sử","Ai là vị vua cuối cùng của triều đại nhà Lý?",Arrays.asList("Lý Thái Tổ","Lý Chiêu Hoàng","Lý Huệ Tông","Lý Nhân Tông"),2,0));
        questionList.add(new Question(43,"Lịch sử","Chiến thắng Điện Biên Phủ diễn ra vào năm nào?",Arrays.asList("1945","1977","1954","1986"),3,0));
        questionList.add(new Question(44,"Lịch sử","Ai là người sáng lập Đảng Cộng sản Việt Nam?",Arrays.asList("Nguyễn Ái Quốc","Võ Nguyên Giáp","Phạm Hùng","Trường Chinh"),1,0));
        questionList.add(new Question(45,"Lịch sử","Trong kháng chiến chống Mĩ, nhân dân vùng đồng bằng sông Cửu Long đã huấn luyện loại ong nào thành vũ khí đánh giặc?",Arrays.asList("Ong lưỡi cày","Ong đất","Ong nghệ","Ong vò vẽ"),4,0));
        questionList.add(new Question(46,"Lịch sử","Thời kỳ nào được coi là \"Thời kỳ vàng son\" của văn hóa Việt Nam?",Arrays.asList("Đông Sơn","Lý - Trần","Tiền Lê","Trần - Lê"),4,1));
        questionList.add(new Question(47,"Lịch sử","Nước ta mang quốc hiệu Đại Việt từ năm nào?",Arrays.asList("1010","1054","939","931"),2,1));
        questionList.add(new Question(48,"Lịch sử","\"Cổ kim vi chính luận\", văn bản phê phán quan chức đô hộ đầu tiên của nước ta, được sáng tác bởi ai ?",Arrays.asList("Trưng Trắc","Thi Sách","Trưng Nhị","Lê Chân"),2,1));
        questionList.add(new Question(49,"Lịch sử","\"Ngồi yên đợi giặc không bằng trước hãy đem quân ra phá thế mạnh của giặc\", câu nói nổi tiếng này của ai?",Arrays.asList("Trần Quốc Tuấn","Ngô Quyền","Lý Thường Kiệt","Quang Trung"),3,1));
        questionList.add(new Question(50,"Lịch sử","Cuộc chiến tranh thế giới thứ nhất diễn ra trong giai đoạn nào?",Arrays.asList("1914 - 1918","1915 - 1918","1916 - 1919","1917 - 1920"),1,1));

        questionList.add(new Question(51,"Địa lý","Đặc điểm của đất feralit là?",Arrays.asList("Thường có màu đỏ, phèn, chua, nghèo dinh dưỡng","Thường có màu đen, xốp, dễ thoát nước","Thường có màu đỏ vàng, rất màu mỡ","Thường có màu nâu, không thích hợp để trồng lúa"),1,0));
        questionList.add(new Question(52,"Địa lý","Tài nguyên khoáng sản Việt Nam tập trung nhiều nhất ở?",Arrays.asList("Miền Bắc","Miền Trung","Miền Nam","Biển và hải đảo"),1,0));
        questionList.add(new Question(53,"Địa lý","Quần đảo Trường Sa trục thuộc tỉnh nào?",Arrays.asList("Quảng Nam","Quảng Ngãi","Bà Rịa - Vũng Tàu","Khánh Hòa"),4,0));
        questionList.add(new Question(54,"Địa lý","Loại gió có tác động thường xuyên đến lãnh thổ nước ta là?",Arrays.asList("Gió mùa Đông Bắc","Gió Mậu Dịch","Gió phơn","Gió mùa Tây Nam"),2,0));
        questionList.add(new Question(55,"Địa lý","Đâu là vùng chuyên môn hóa về lương thực thực phẩm lớn nhất nước ta?",Arrays.asList("Đồng bằng Sông Hồng","Tây Nguyên","Đồng bằng Sông Cửu Long","Vùng núi Tây Bắc"),3,0));
        questionList.add(new Question(56,"Địa lý","Rừng nhiệt đới Amazon đặc trưng cho khu vực nào?",Arrays.asList("Nam Mỹ","Bắc Mỹ","Châu Phi","Nam Cực"),1,1));
        questionList.add(new Question(57,"Địa lý","Lục địa nào chiếm diện tích lớn nhất trên thế giới?",Arrays.asList("Châu Phi","Châu Âu","Châu Mỹ","Châu Á"),4,1));
        questionList.add(new Question(58,"Địa lý","Thành phố nào được biết đến là \"Thành phố của những cây cầu\"?",Arrays.asList("Paris","Venice","San Francisco","Prague"),3,1));
        questionList.add(new Question(59,"Địa lý","Đâu là dòng sông dài nhất thế giới?",Arrays.asList("Sông Amazon","Sông Hằng","Sông Mississippi","Sông Nile"),4,1));
        questionList.add(new Question(60,"Địa lý","Việt Nam có bao nhiêm km đường bờ biển?",Arrays.asList("2630 km","3260 km","2360 km","3620 km"),2,1));

        questionList.add(new Question(61,"Công nghệ","Đâu là hệ điều hành phổ biến cho máy tính cá nhân?",Arrays.asList("Windows","Mac","MacOS", "Linus"),1,0));
        questionList.add(new Question(62,"Công nghệ","Kích thước lưu trữ thông thường của một ổ đĩa USB tiêu chuẩn là bao nhiêu gigabyte?",Arrays.asList("8 GB","16 GB","32 GB","Tất cả đều đúng"),4,0));
        questionList.add(new Question(63,"Công nghệ","Tên người sáng lập Microsoft là ai?",Arrays.asList("Mark Zuckerberg","Steve Jobs","Bill Gates","Larry Page"),3,0));
        questionList.add(new Question(64,"Công nghệ","Định dạng file hình ảnh phổ biến nhất là gì?",Arrays.asList("BMP","PNG","JPG/JPEG","GIF"),3,0));
        questionList.add(new Question(65,"Công nghệ","\"HTTP\" là viết tắt của gì trong ngữ cảnh của trang web?",Arrays.asList("HyperText Transfer Protocol","High-Tech Transfer Protocol","Hypertext Technical Protocol","High Transfer Technology Protocol"),1,0));
        questionList.add(new Question(66,"Công nghệ","Loại màn hình nào có độ phân giải cao hơn giúp hiển thị hình ảnh và văn bản chi tiết hơn?",Arrays.asList("LCD","LED","OLED","4K"),4,1));
        questionList.add(new Question(67,"Công nghệ","Thuật ngữ \"Phần mềm mã nguồn mở\" có ý nghĩa gì?",Arrays.asList("Phần mềm được phát triển bởi một công ty duy nhất","Mã nguồn mà mọi người có thể xem, sửa đổi, và phân phối miễn phí","Phần mềm có giá trị cao","Phần mềm chỉ dành cho mục đích giáo dục"),2,1));
        questionList.add(new Question(68,"Công nghệ","Tên người sáng lập SpaceX và Tesla là ai?",Arrays.asList("Jeff Bezos","Elon Musk","Mark Zuckerberg","Larry Page"),2,1));
        questionList.add(new Question(69,"Công nghệ","Khái niệm \"Internet of Things\" (IoT) liên quan đến việc gì?",Arrays.asList("Kết nối Internet qua Wi-Fi","Sự kết nối giữa các máy tính cá nhân","Sự kết nối giữa các quốc gia qua Internet","Kết nối của các thiết bị thông minh"),4,1));
        questionList.add(new Question(70,"Công nghệ","Đâu là loại kết nối mạng không dây phổ biến được sử dụng để kết nối thiết bị di động và máy tính bảng?",Arrays.asList("GPRS","Wi-fi","Bluetooth","NFC"),2,1));

        questionList.add(new Question(71,"Thiên văn học","Hệ Mặt Trời của chúng ta có mấy hành tinh?",Arrays.asList("6","7","8","9"),3,0));
        questionList.add(new Question(72,"Thiên văn học","Tên của hành tinh đỏ nổi tiếng trong Hệ Mặt Trời là gì?",Arrays.asList("Venus","Mars","Earth","Saturn"),2,0));
        questionList.add(new Question(73,"Thiên văn học","Ngôi sao nào là nguồn năng lượng chính cho hệ mặt trời?",Arrays.asList("Alpha Centauri","Sirius","Proximal Centauri","Mặt trời"),4,0));
        questionList.add(new Question(74,"Thiên văn học","Chất khí nào chiếm phần lớn trong khí quyển của Trái Đất?",Arrays.asList("Nitơ","Oxy","Argon","Cacbon dioxidde"),1,0));
        questionList.add(new Question(75,"Thiên văn học","Hành tinh nào gần Mặt trời nhất?",Arrays.asList("Venus","Earth","Uranus","Jupiter"),1,0));
        questionList.add(new Question(76,"Thiên văn học","Loại thiên thạch nào gây ra sự tàn phá lớn nhất trên Trái Đất khoảng 66 triệu năm trước?",Arrays.asList("Quasar","Asteroid","Comet","Meteoroid"),2,1));
        questionList.add(new Question(77,"Thiên văn học","Đâu là hành tinh lớn nhất trong Hệ Mặt Trời?",Arrays.asList("Jupiter","Saturn","Mars","Neptune"),1,1));
        questionList.add(new Question(78,"Thiên văn học","Neil Armstrong đặt chân lên mặt trăng lần đầu tiên vào năm bao nhiêu?",Arrays.asList("1990","1969","1972","1982"),2,1));
        questionList.add(new Question(79,"Thiên văn học","Hiện tượng ngày và đêm xảy ra do đặc điểm gì của Trái Đất?",Arrays.asList("Chiếu sáng từ Mặt Trời","Sự xoay quanh trục","Hành tinh di chuyển quanh Mặt Trời","Sự thay đổi của địa hình Trái Đất"),2,1));
        questionList.add(new Question(80,"Thiên văn học","Hố đen là gì?",Arrays.asList("Một loại sao siêu lớn","Một vùng không gian không có vật thể","Một quá trình phát xạ ánh sáng","Một khu vực trong không gian có trọng lực siêu mạnh, đến nổi không thể thoát ra được cả ánh sáng"),4,1));

        questionList.add(new Question(81,"Toán học", "Tính giá trị của biểu thức: 2 + 3", Arrays.asList("4", "5", "6", "7"), 2, 0));
        questionList.add(new Question(82,"Toán học", "Hình vuông có bao nhiêu cạnh?", Arrays.asList("2", "3", "4", "5"), 3, 0));
        questionList.add(new Question(83,"Toán học", "Nếu A + B = 15 và A - B = 5 thì giá trị của A là bao nhiêu?", Arrays.asList("10", "5", "8", "7"), 1, 0));
        questionList.add(new Question(84,"Toán học", "Tính giá trị của biểu thức: sin(30°)", Arrays.asList("0", "1/2", "1", "√3/2"), 2, 0));
        questionList.add(new Question(85,"Toán học", "Giải phương trình: 2x - 7 = 3", Arrays.asList("x = 5", "x = -5", "x = 2", "x = 4"), 1, 0));
        questionList.add(new Question(86,"Toán học", "Nếu hôm nay là thứ Năm thì ngày mai là thứ mấy?", Arrays.asList("Thứ Sáu", "Thứ Bảy", "Chủ nhật", "Thứ Hai"), 1, 0));
        questionList.add(new Question(87,"Toán học", "Cho tam giác có cạnh a = 3 cm, cạnh b = 4 cm, cạnh c = 5 cm. Tam giác đó là tam giác gì?", Arrays.asList("Tam giác vuông", "Tam giác đều", "Tam giác cân", "Tam giác nhọn"), 1, 0));
        questionList.add(new Question(88,"Toán học", "Gieo đồng xu 3 lần. Xác suất để được mặt sấp ít nhất 1 lần là bao nhiêu?", Arrays.asList("7/8", "5/8", "3/8", "1/8"), 1, 0));
        questionList.add(new Question(89,"Toán học", "Nếu 4 con gà ấp 4 quả trứng trong 4 ngày là xong. Vậy cần bao nhiêu ngày để 8 con gà ấp xong 8 quả trứng?", Arrays.asList("1 ngày", "2 ngày", "3 ngày", "4 ngày"), 4, 0));
        questionList.add(new Question(90,"Toán học", "Số nào là số chính phương: 25, 36, 49, 64", Arrays.asList("25", "35", "49", "64"), 2, 0));
        questionList.add(new Question(91,"Toán học", "Tính giá trị của biểu thức: √(25 * 4)", Arrays.asList("10", "20", "15", "25"), 2, 1));
        questionList.add(new Question(92,"Toán học", "Hình chữ nhật có chu vi là 30 cm và chiều rộng là 5 cm. Tính chiều dài của hình chữ nhật đó.", Arrays.asList("6 cm", "7 cm", "8 cm", "9 cm"), 3, 1));
        questionList.add(new Question(93,"Toán học", "Nếu A + B = 20 và A - B = 8 thì giá trị của B là bao nhiêu?", Arrays.asList("6", "5", "8", "7"), 4, 1));
        questionList.add(new Question(94,"Toán học", "Tính giá trị của biểu thức: log2(8)", Arrays.asList("1", "2", "3", "4"), 1, 1));
        questionList.add(new Question(95,"Toán học", "Giải phương trình: x^2 - 4 = 0", Arrays.asList("x = 2", "x = -2", "x = 4", "x = -4"), 2, 1));
        questionList.add(new Question(96,"Toán học", "Nếu hôm nay là thứ Hai thì ngày hôm qua là thứ mấy?", Arrays.asList("Thứ Bảy", "Chủ nhật", "Thứ Sáu", "Thứ Năm"), 3, 1));
        questionList.add(new Question(97,"Toán học", "Cho tam giác có cạnh a = 7 cm, cạnh b = 24 cm, cạnh c = 25 cm. Tam giác đó là tam giác gì?", Arrays.asList("Tam giác vuông", "Tam giác đều", "Tam giác cân", "Tam giác nhọn"), 1, 1));
        questionList.add(new Question(98,"Toán học", "Gieo đồng tiền 4 lần cân đối và đồng chất. Xác suất để được ít nhất một lần xuất hiện mặt sấp là bao nhiêu?", Arrays.asList("15/16", "13/16", "11/16", "9/16"), 1, 1));
        questionList.add(new Question(99,"Toán học", "Nếu 6 con gà ấp 6 quả trứng trong 6 ngày là xong. Vậy cần bao nhiêu ngày để 12 con gà ấp xong 12 quả trứng?", Arrays.asList("1 ngày", "2 ngày", "3 ngày", "4 ngày"), 3, 1));
        questionList.add(new Question(100,"Toán học", "Số nào không phải là số nguyên tố: 37, 41, 43, 47", Arrays.asList("37", "41", "43", "46"), 4, 1));
        questionList.add(new Question(101,"Vật lý", "Đơn vị đo năng lượng là gì?", Arrays.asList("Joule", "Watt", "Volt", "Newton"), 1, 0));
        questionList.add(new Question(102,"Vật lý", "Quả cầu nào có khối lượng lớn nhất?", Arrays.asList("Quả cầu sắt", "Quả cầu gỗ", "Quả cầu nhựa", "Cùng khối lượng"), 4, 0));
        questionList.add(new Question(103,"Vật lý", "Tính khối lượng của một vật khi biết trọng lực và gia tốc trọng trường.", Arrays.asList("m = F/g", "m = Fg", "m = F + g", "m = F/g²"), 1, 0));
        questionList.add(new Question(104,"Vật lý", "Đơn vị đo công suất là gì?", Arrays.asList("Joule", "Watt", "Newton", "Volt"), 2, 0));
        questionList.add(new Question(105,"Vật lý", "Theo quy luật bảo toàn năng lượng, tổng năng lượng của một hệ thống đóng không đổi trong quá trình gì?", Arrays.asList("Tăng giảm", "Tăng", "Giảm", "Không đổi"), 4, 0));
        questionList.add(new Question(106,"Vật lý", "Ánh sáng trắng khi đi qua kính lăng tâm có hiện tượng gì?", Arrays.asList("Tán xạ", "Phản xạ", "Nội suy", "Phân tán"), 3, 0));
        questionList.add(new Question(107,"Vật lý", "Độ chói của một đèn phản xạ bề mặt phụ thuộc vào điều gì?", Arrays.asList("Cường độ ánh sáng", "Số lượng ánh sáng", "Màu sắc ánh sáng", "Góc nghiêng"), 4, 0));
        questionList.add(new Question(108,"Vật lý", "Dòng điện đi qua một dây dẫn tạo ra hiện tượng gì?", Arrays.asList("Tia laser", "Tia X", "Tia nhiệt", "Tia điện từ"), 3, 0));
        questionList.add(new Question(109,"Vật lý", "Áp suất của một hơi khí tăng khi làm gì?", Arrays.asList("Nung nóng", "Làm lạnh", "Tăng số lượng", "Giảm thể tích"), 1, 0));
        questionList.add(new Question(110,"Vật lý", "Loại năng lượng nào không thể chuyển đổi thành năng lượng khác?", Arrays.asList("Năng lượng cơ", "Năng lượng nhiệt", "Năng lượng hạt nhân", "Năng lượng điện từ"), 3, 0));
        questionList.add(new Question(111,"Vật lý", "Công thức nào dùng để tính lực đàn hồi của lò xo?", Arrays.asList("F = kx", "F = mx", "F = mg", "F = P/A"), 1, 1));
        questionList.add(new Question(112,"Vật lý", "Hiện tượng nào giải thích sự cản trở của không khí đối với vật chuyển động?", Arrays.asList("Áp suất", "Sức cản", "Lực đàn hồi", "Lực ma sát"), 4, 1));
        questionList.add(new Question(113,"Vật lý", "Theo lý thuyết tương đối của Einstein, vật chuyển động có thể làm gì?", Arrays.asList("Co lại", "Mở rộng", "Giảm khối lượng", "Tăng khối lượng"), 3, 1));
        questionList.add(new Question(114,"Vật lý", "Làm thế nào để tăng nhiệt độ của một hơi khí mà không làm thay đổi áp suất?", Arrays.asList("Nén nó", "Làm lạnh nó", "Gia tăng thể tích", "Tăng số lượng"), 3, 1));
        questionList.add(new Question(115,"Vật lý", "Quả cầu nào có độ bám nhất trên mặt trái đất?", Arrays.asList("Quả cầu nhựa", "Quả cầu gỗ", "Quả cầu kim loại", "Cùng bám nhau"), 2, 1));
        questionList.add(new Question(116,"Vật lý", "Năng lượng của nước dâng cao được chuyển đổi từ nguồn năng lượng nào?", Arrays.asList("Năng lượng mặt trời", "Năng lượng điện từ", "Năng lượng hạt nhân", "Năng lượng cơ học"), 1, 1));
        questionList.add(new Question(117,"Vật lý", "Đối với vật chuyển động trong không trung, lực nào đóng vai trò chính trong việc giữ cho vật chuyển động theo quỹ đạo cong?", Arrays.asList("Trọng lực", "Lực đàn hồi", "Lực đối trọng", "Lực ly tâm"), 4, 1));
        questionList.add(new Question(118,"Vật lý", "Thuyết tương đối của Einstein nói về mối quan hệ giữa thời gian và không gian trong điều kiện nào?", Arrays.asList("Điều kiện chân không", "Vận tốc gần vận tốc ánh sáng", "Nhiệt độ thấp", "Nhiệt độ cao"), 2, 1));
        questionList.add(new Question(119,"Vật lý", "Dòng điện xoay chiều tạo ra hiện tượng gì?", Arrays.asList("Tia điện từ", "Tia X", "Tia laser", "Tia nhiệt"), 1, 1));
        questionList.add(new Question(120,"Vật lý", "Loại sóng nào có tần số cao hơn trong phổ âm thanh?", Arrays.asList("Âm trầm", "Âm bass", "Âm alto", "Âm treble"), 4, 1));
        questionList.add(new Question(121,"Hóa học", "Nguyên tố nào chiếm tỷ lệ lớn nhất trong hơi khí quyển?", Arrays.asList("Oxygen", "Hydrogen", "Nitrogen", "Carbon"), 3, 0));
        questionList.add(new Question(122,"Hóa học", "Công thức hóa học của nước là gì?", Arrays.asList("H2O2", "CO2", "H2O", "CH4"), 3, 0));
        questionList.add(new Question(123,"Hóa học", "Nguyên tố nào chiếm tỷ lệ cao nhất trong vỏ Trái Đất?", Arrays.asList("Aluminum", "Silicon", "Oxygen", "Iron"), 3, 0));
        questionList.add(new Question(124,"Hóa học", "Đơn vị đo nhiệt độ trong hệ thống SI là gì?", Arrays.asList("Fahrenheit", "Celsius", "Kelvin", "Rankine"), 3, 0));
        questionList.add(new Question(125,"Hóa học", "Trạng thái của nước ở nhiệt độ 100 độ C và áp suất 1 atm là gì?", Arrays.asList("Hơi", "Lỏng", "Rắn", "Plasma"), 1, 0));
        questionList.add(new Question(126,"Hóa học", "Công thức hóa học của axit sulfuric là gì?", Arrays.asList("H2SO4", "HCl", "HNO3", "H3PO4"), 1, 0));
        questionList.add(new Question(127,"Hóa học", "Nguyên tố nào tạo ra phổ màu xanh lam khi đốt cháy?", Arrays.asList("Copper", "Sodium", "Lithium", "Barium"), 1, 0));
        questionList.add(new Question(128,"Hóa học", "Khí nào chiếm tỷ lệ cao nhất trong không khí quyển?", Arrays.asList("Oxygen", "Nitrogen", "Argon", "Carbon Dioxide"), 2, 0));
        questionList.add(new Question(129,"Hóa học", "Phản ứng nào làm thay đổi màu của giấy quỳ?", Arrays.asList("Oxidation", "Reduction", "Neutralization", "Hydrolysis"), 1, 0));
        questionList.add(new Question(130,"Hóa học", "Loại hợp chất nào có phân tử chứa các nguyên tố khác nhau?", Arrays.asList("Nguyên tố", "Hợp chất phức tạp", "Hợp chất đơn", "Hợp chất nguyên tố"), 2, 0));
        questionList.add(new Question(131,"Hóa học", "Quá trình nào giải thích sự biến đổi của các nguyên tử để tạo ra các phân tử khác nhau?", Arrays.asList("Tách nước", "Photosynthesis", "Nuclear fission", "Chemical reaction"), 4, 1));
        questionList.add(new Question(132,"Hóa học", "Số nguyên tử trong một phân tử acid acetic (CH3COOH) là bao nhiêu?", Arrays.asList("5", "6", "7", "8"), 2, 1));
        questionList.add(new Question(133,"Hóa học", "Nhiệt động học học nói về điều gì trong quá trình hóa học?", Arrays.asList("Tốc độ phản ứng", "Năng lượng thay đổi", "Cân bằng phản ứng", "Tích số lượng chất tham gia"), 2, 1));
        questionList.add(new Question(134,"Hóa học", "Công thức hóa học của metan là gì?", Arrays.asList("CH3OH", "C2H6", "CO2", "CH4"), 4, 1));
        questionList.add(new Question(135,"Hóa học", "Loại phản ứng hóa học nào là sự kết hợp giữa hydro và oxi để tạo ra nước?", Arrays.asList("Hydrolysis", "Combustion", "Synthesis", "Decomposition"), 2, 1));
        questionList.add(new Question(136,"Hóa học", "Nguyên tố nào chiếm tỷ lệ cao nhất trong hợp kim đồng?", Arrays.asList("Zinc", "Copper", "Tin", "Lead"), 2, 1));
        questionList.add(new Question(137,"Hóa học", "Phản ứng trao đổi ion làm thay đổi gì trong chất lỏng?", Arrays.asList("Màu sắc", "Độ dẫn điện", "Mùi", "Nhiệt độ"), 2, 1));
        questionList.add(new Question(138,"Hóa học", "Nguyên tắc bảo toàn nguyên tử nói rằng:", Arrays.asList("Nguyên tử không thể bị phá hủy", "Nguyên tử không thể tạo ra từ hỏa", "Khối lượng nguyên tử không thay đổi trong một phản ứng hóa học", "Nguyên tử không thể tách ra khỏi phân tử"), 3, 1));
        questionList.add(new Question(139,"Hóa học", "Cấu trúc electron của nguyên tử được mô tả bằng gì?", Arrays.asList("Sơ đồ Lewis", "Định luật Dalton", "Bảng tuần hoàn", "Lí thuyết sóng"), 1, 1));
        questionList.add(new Question(140,"Hóa học", "Phản ứng nào tạo ra khí nitrous (N2O)?", Arrays.asList("Combustion", "Decomposition", "Synthesis", "Denitrification"), 4, 1));
        questionList.add(new Question(141, "Sinh học", "Cơ quan nào chịu trách nhiệm sản xuất hormone insulin?", Arrays.asList("Tuyến thượng thận", "Tuyến nội tiết", "Tuyến giáp", "Tuyến mang tai"), 2, 0));
        questionList.add(new Question(142, "Sinh học", "Quá trình quang hợp diễn ra ở bộ phận nào của tế bào cây?", Arrays.asList("Mitochondria", "Lysosome", "Thylakoid", "Ribosome"), 3, 0));
        questionList.add(new Question(143, "Sinh học", "Chất nào đóng vai trò quan trọng trong truyền dẫn thông tin gen?", Arrays.asList("Protein", "Lipid", "Carbohydrate", "Nucleic acid"), 1, 0));
        questionList.add(new Question(144, "Sinh học", "Vi khuẩn thuộc vào nhóm vi khuẩn nào?", Arrays.asList("Archaebacteria", "Eubacteria", "Protista", "Fungi"), 2, 0));
        questionList.add(new Question(145, "Sinh học", "Sự phân biệt tế bào giữa các tế bào khác nhau của cơ thể được gọi là gì?", Arrays.asList("Mitosis", "Meiosis", "Differentiation", "Apoptosis"), 3, 0));
        questionList.add(new Question(146, "Sinh học", "Đâu không phải là cơ quan tiêu hóa trong cơ thể người?", Arrays.asList("Dạ dày", "Lá lách", "Ruột non", "Gan"), 2, 0));
        questionList.add(new Question(147, "Sinh học", "Loại tế bào nào có khả năng tự tái tạo và chia thành nhiều loại tế bào khác nhau?", Arrays.asList("Tế bào gốc", "Tế bào não", "Tế bào cơ", "Tế bào da"), 1, 0));
        questionList.add(new Question(148, "Sinh học", "Chất nào đóng vai trò quan trọng trong việc truyền tín hiệu giữa các tế bào?", Arrays.asList("DNA", "RNA", "Protein", "Lipid"), 3, 0));
        questionList.add(new Question(149, "Sinh học", "Quá trình hô hấp tế bào diễn ra ở bộ phận nào của tế bào?", Arrays.asList("Mitochondria", "Nucleus", "Endoplasmic reticulum", "Lysosome"), 1, 0));
        questionList.add(new Question(150, "Sinh học", "Cơ quan nào đóng vai trò chính trong việc duy trì cân nặng của cơ thể?", Arrays.asList("Tim", "Gan", "Tuyến thượng thận", "Tuyến giáp"), 2, 0));
        questionList.add(new Question(151, "Sinh học", "Phân biệt giữa tế bào thực thể (somatic cells) và tế bào germen (germ cells).", Arrays.asList("Tế bào thực thể là tế bào sinh sản, tế bào germen là tế bào cơ bản", "Tế bào thực thể có nhiều hình dạng khác nhau, tế bào germen có hình dạng đồng đều", "Tế bào thực thể không tham gia vào quá trình phôi thai, tế bào germen tham gia vào quá trình phôi thai", "Tế bào thực thể chia thành tế bào con qua quá trình meiosis, tế bào germen chia thành tế bào con qua quá trình mitosis"), 4, 1));
        questionList.add(new Question(152, "Sinh học", "Giải thích cơ chế hoạt động của hormone insulin trong việc điều chỉnh đường huyết.", Arrays.asList("Insulin tăng huyết đường bằng cách kích thích gan sản xuất đường", "Insulin giảm huyết đường bằng cách kích thích tế bào hấp thụ đường", "Insulin giảm huyết đường bằng cách kích thích tăng sản xuất glugacon", "Insulin tăng huyết đường bằng cách giảm sự hấp thụ đường của tế bào"), 2, 1));
        questionList.add(new Question(153, "Sinh học", "Tại sao tế bào thực thể (somatic cells) lại có số lượng nhiễm sắc thể gấp đôi so với tế bào germen?", Arrays.asList("Để đảm bảo tế bào thực thể có đủ gen để thực hiện các chức năng cơ bản", "Để tế bào germen có thể giữ được số lượng gen cố định", "Để tế bào thực thể chia thành nhiều tế bào con khi cần thiết", "Để tế bào germen có thể tham gia vào quá trình phôi thai"), 4, 1));
        questionList.add(new Question(154, "Sinh học", "Mô tả cơ chế hoạt động của enzyme trong quá trình tiêu hóa thức ăn.", Arrays.asList("Enzyme tăng tốc độ phản ứng hóa học mà không bị tiêu hao", "Enzyme hấp thụ chất dinh dưỡng từ thức ăn", "Enzyme phân giải chất thải trong cơ thể", "Enzyme tạo ra năng lượng từ thức ăn"), 1, 1));
        questionList.add(new Question(155, "Sinh học", "So sánh giữa quá trình respiration ở tế bào thực thể và tế bào germen.", Arrays.asList("Respiration ở tế bào thực thể tạo năng lượng hơn so với tế bào germen", "Respiration ở tế bào thực thể không liên quan đến việc tiêu thụ oxy", "Respiration ở tế bào thực thể tạo ra CO2 nhưng tế bào germen không", "Respiration ở tế bào germen tạo năng lượng chủ yếu từ quá trình fermentation"), 3, 1));
        questionList.add(new Question(156, "Sinh học", "Giải thích cơ chế hoạt động của thụ thể hormone trên bề mặt tế bào.", Arrays.asList("Thụ thể hormone tăng cường khả năng phân giải chất dinh dưỡng", "Thụ thể hormone làm tăng tỷ lệ phân chia tế bào", "Thụ thể hormone chuyển đổi hormone thành năng lượng", "Thụ thể hormone truyền đạt tín hiệu từ hormone vào tế bào"), 4, 1));
        questionList.add(new Question(157, "Sinh học", "Tại sao quá trình mitosis quan trọng đối với sự phát triển của cơ thể?", Arrays.asList("Mitosis giúp giảm số lượng tế bào", "Mitosis tạo ra tế bào con giống nhau với tế bào cha mẹ", "Mitosis tạo ra tế bào con có đặc điểm mới so với tế bào cha mẹ", "Mitosis ngăn chặn quá trình phân giải gen trong tế bào"), 2, 1));
        questionList.add(new Question(158, "Sinh học", "Mô tả vai trò của tế bào germen trong quá trình phôi thai.", Arrays.asList("Tế bào germen tham gia vào quá trình mitosis để tạo ra tế bào con", "Tế bào germen chuyển đổi thành tế bào thực thể trong quá trình phôi thai", "Tế bào germen tạo ra tế bào con qua quá trình meiosis", "Tế bào germen giữ nguyên gen không thay đổi"), 3, 1));
        questionList.add(new Question(159, "Sinh học", "Giải thích cơ chế hoạt động của tế bào germen trong quá trình phôi thai.", Arrays.asList("Tế bào germen tham gia vào quá trình mitosis để tạo ra tế bào con", "Tế bào germen chuyển đổi thành tế bào thực thể trong quá trình phôi thai", "Tế bào germen tạo ra tế bào con qua quá trình meiosis", "Tế bào germen giữ nguyên gen không thay đổi"), 3, 1));
        questionList.add(new Question(160, "Sinh học", "Giải thích tại sao quá trình meiosis là quan trọng đối với sự đa dạng gen trong các loài.", Arrays.asList("Meiosis giảm số lượng gen trong tế bào con", "Meiosis tạo ra tế bào con giống nhau với tế bào cha mẹ", "Meiosis tạo ra tế bào con có đặc điểm mới so với tế bào cha mẹ", "Meiosis tạo ra sự đa dạng gen trong tế bào con"), 4, 1));
        questionList.add(new Question(161, "Lịch sử", "Sự kiện nào đánh dấu sự kết thúc Chiến tranh thế giới thứ nhất?", Arrays.asList("Hiệp ước Versailles", "Trận đánh Somme", "Ký hiệp ước hòa bình", "Nổ súng bắn chết Archduke Franz Ferdinand"), 1, 0));
        questionList.add(new Question(162, "Lịch sử", "Ai là người lãnh đạo của Cách mạng Công nghiệp ở Anh?", Arrays.asList("Napoleon Bonaparte", "Queen Victoria", "Oliver Cromwell", "James Watt"), 3, 0));
        questionList.add(new Question(163, "Lịch sử", "Tổ chức nào được thành lập sau Chiến tranh thế giới thứ hai để duy trì hòa bình thế giới?", Arrays.asList("Liên minh Soviet", "Liên Hiệp Quốc", "Hoàng gia Liên hiệp Anh", "Quốc hội Pháp"), 2, 0));
        questionList.add(new Question(164, "Lịch sử", "Sự kiện nào đánh dấu sự kết thúc Chiến tranh lạnh?", Arrays.asList("Sự kiện Đức hòa bình", "Sự kiện Suez", "Sự kiện Cuba", "Sự kiện Berlin"), 1, 0));
        questionList.add(new Question(165, "Lịch sử", "Ai là vị lãnh tụ nổi tiếng của nền văn minh Hy Lạp cổ đại?", Arrays.asList("Alexander the Great", "Julius Caesar", "Pericles", "Cleopatra"), 3, 0));
        questionList.add(new Question(166, "Lịch sử", "Thành phố nào được coi là trung tâm văn hóa của Đế chế Byzantine?", Arrays.asList("Rome", "Constantinople", "Athens", "Alexandria"), 2, 0));
        questionList.add(new Question(167, "Lịch sử", "Ai là vị lãnh tụ của Phong trào Dân chủ ở Ấn Độ?", Arrays.asList("Jawaharlal Nehru", "Indira Gandhi", "Mahatma Gandhi", "Subhas Chandra Bose"), 3, 0));
        questionList.add(new Question(168, "Lịch sử", "Thời kỳ nào được gọi là \"Thời kỳ Hoàng kim\" của Đế quốc La Mã?", Arrays.asList("Thời kỳ Cộng hòa", "Thời kỳ Cesar", "Thời kỳ Pax Romana", "Thời kỳ Quốc sĩ La Mã"), 3, 0));
        questionList.add(new Question(169, "Lịch sử", "Ai là người sáng lập và lãnh đạo Đảng Cộng sản Trung Quốc?", Arrays.asList("Sun Yat-sen", "Chiang Kai-shek", "Mao Zedong", "Deng Xiaoping"), 3, 0));
        questionList.add(new Question(170, "Lịch sử", "Ai là vị lãnh tụ của nền văn minh Ai Cập cổ đại?", Arrays.asList("Cleopatra", "Ramesses II", "Hatshepsut", "Khufu"), 2, 0));
        questionList.add(new Question(171, "Lịch sử", "Mô tả hệ thống xã hội của Pharaon Ai Cập cổ đại.", Arrays.asList("Xã hội phân biệt giai cấp, với pharaoh ở đỉnh", "Xã hội Ai Cập không có phân biệt giai cấp", "Pharaoh chỉ là biểu tượng, không có quyền lực thực tế", "Nô lệ đóng vai trò quan trọng trong xã hội Ai Cập"), 1, 1));
        questionList.add(new Question(172, "Lịch sử", "Những sự kiện nào đã dẫn đến và kết thúc cuộc Cách mạng Pháp?", Arrays.asList("Tự do, Bình đẳng, An toàn", "Khởi nghĩa dân chủ, Chỉ huy quân sự", "Chiến tranh thế giới, Hiệp ước Versailles", "Cách mạng công nghiệp, Đại dịch bệnh hạch"), 3, 1));
        questionList.add(new Question(173, "Lịch sử", "Tác động của Cuộc Cách mạng Công nghiệp đối với xã hội và kinh tế.", Arrays.asList("Tăng cường quyền lực của địa chủ", "Gây ra sự suy thoái kinh tế", "Tạo ra sự phân chia giai cấp sâu rộng", "Dẫn đến sự phát triển kinh tế và công nghiệp"), 4, 1));
        questionList.add(new Question(174, "Lịch sử", "Sự quan trọng của Chiến tranh thế giới thứ nhất trong lịch sử thế giới.", Arrays.asList("Giảm sút quyền lực của các đế quốc châu Âu", "Tạo ra một thế giới mới với nhiều liên minh quốc tế", "Dẫn đến việc hủy bỏ hiệp ước Versailles", "Gây ra suy thoái kinh tế toàn cầu"), 2, 1));
        questionList.add(new Question(175, "Lịch sử", "Những thay đổi lớn nào đã xảy ra trong xã hội châu Âu sau Cuộc Cách mạng Công nghiệp?", Arrays.asList("Giảm sút quyền lực của giai cấp công nhân", "Sự xuất hiện của giai cấp trung lưu mới", "Sự giữ nguyên của hệ thống phong kiến", "Thay đổi lớn trong cơ cấu giai cấp và xã hội"), 4, 1));
        questionList.add(new Question(176, "Lịch sử", "Nguyên nhân dẫn đến sự sụp đổ của Đế chế La Mã.", Arrays.asList("Xâm lược của quân Mông Cổ", "Cuộc Cách mạng nông dân", "Sự kiện Sack of Rome", "Sự suy thoái nội bộ và sự tấn công từ ngoại bang"), 4, 1));
        questionList.add(new Question(177, "Lịch sử", "Những vị lãnh tụ nào đã đóng góp vào việc định hình châu Âu sau Chiến tranh thế giới thứ hai?", Arrays.asList("Winston Churchill, Franklin D. Roosevelt, Joseph Stalin", "Mao Zedong, Ho Chi Minh, Kim Il-sung", "Charles de Gaulle, Benito Mussolini, Francisco Franco", "Nikita Khrushchev, John F. Kennedy, Mao Zedong"), 1, 1));
        questionList.add(new Question(178, "Lịch sử", "Những biện pháp nào đã được Adolf Hitler thực hiện để xây dựng Đế chế Đức Quốc xã?", Arrays.asList("Chính sách đối thoại hòa bình", "Chính sách Mặt trận Lập hiến", "Chính sách Chiến tranh Blitzkrieg", "Chính sách Pháp lập hiến"), 3, 1));
        questionList.add(new Question(179, "Lịch sử", "Tác động của Cuộc Cách mạng Bolshevik đối với nước Nga và thế giới.", Arrays.asList("Dẫn đến sự thất bại của tư bản chủ nghĩa", "Tạo ra một chính thức liên bang với các quốc gia khác", "Gây ra sự phân chia xã hội sâu rộng", "Dẫn đến sự thành lập Liên bang Xã hội chủ nghĩa Xô viết"), 4, 1));
        questionList.add(new Question(180, "Lịch sử", "Tầm quan trọng của Cách mạng Pháp đối với sự phát triển chính trị và xã hội châu Âu.", Arrays.asList("Tạo ra sự ổn định và hòa bình", "Dẫn đến sự xuất hiện của chủ nghĩa tư bản", "Gây ra sự suy thoái kinh tế", "Dẫn đến sự gióng lên của các phong trào dân chủ và cộng sản"), 2, 1));
        questionList.add(new Question(181, "Địa lý", "Dãy núi nào chia cắt châu Á và châu Âu?", Arrays.asList("Dãy Himalaya", "Dãy Alps", "Dãy Ural", "Dãy Carpathian"), 3, 0));
        questionList.add(new Question(182, "Địa lý", "Thác nước nổi tiếng Niagara nằm giữa hai quốc gia nào?", Arrays.asList("Canada và Hoa Kỳ", "Hoa Kỳ và México", "Canada và Pháp", "Canada và Anh"), 1, 0));
        questionList.add(new Question(183, "Địa lý", "Đất nước nào nằm ở bờ biển đông của Biển Đen?", Arrays.asList("Turkey", "Greece", "Italy", "Ukraine"), 1, 0));
        questionList.add(new Question(184, "Địa lý", "Hồ Baikal, hồ nước ngọt sâu nhất thế giới, nằm ở quốc gia nào?", Arrays.asList("Mongolia", "Kazakhstan", "Russia", "China"), 3, 0));
        questionList.add(new Question(185, "Địa lý", "Thành phố nào được biết đến với biệt danh 'Thành phố Ánh sáng'?", Arrays.asList("New York City", "Tokyo", "Paris", "Rome"), 1, 0));
        questionList.add(new Question(186, "Địa lý", "Dòng sông nào là dòng sông dài nhất thế giới?", Arrays.asList("Sông Amazon", "Sông Nile", "Sông Yangtze", "Sông Mekong"), 1, 0));
        questionList.add(new Question(187, "Địa lý", "Đảo quốc nào nằm ở ngoại ô của thủ đô Dublin?", Arrays.asList("Iceland", "Greenland", "Faroe Islands", "Ireland"), 4, 0));
        questionList.add(new Question(188, "Địa lý", "Thành phố nào là thủ đô của nước Nhật Bản?", Arrays.asList("Seoul", "Beijing", "Tokyo", "Bangkok"), 3, 0));
        questionList.add(new Question(189, "Địa lý", "Đảo quốc nào nằm gần bờ biển đông của Australia?", Arrays.asList("New Zealand", "Papua New Guinea", "Indonesia", "Fiji"), 1, 0));
        questionList.add(new Question(190, "Địa lý", "Thành phố nào được biết đến với tên gọi 'Thành phố tình yêu'?", Arrays.asList("Paris", "Venice", "Prague", "Rome"), 2, 0));
        questionList.add(new Question(191, "Địa lý", "Đặc điểm địa lý của sa mạc Sahara và sa mạc Gobi là gì?", Arrays.asList("Cả hai đều nằm ở châu Phi", "Cả hai đều nằm ở châu Á", "Cả hai đều có dạng bán cầu khô", "Cả hai đều nằm ở châu Âu"), 2, 1));
        questionList.add(new Question(192, "Địa lý", "Vịnh nào được coi là 'Nửa trái tim mặt trời' tại Việt Nam?", Arrays.asList("Vịnh Hạ Long", "Vịnh Cam Ranh", "Vịnh Vân Phong", "Vịnh Bắc Bộ"), 1, 1));
        questionList.add(new Question(193, "Địa lý", "Thị trấn nào nằm ở nơi gặp gỡ giữa sông Nile và sông White Nile?", Arrays.asList("Cairo", "Luxor", "Aswan", "Khartoum"), 4, 1));
        questionList.add(new Question(194, "Địa lý", "Nước nào là quốc gia lớn nhất trên thế giới về diện tích đất liền?", Arrays.asList("Canada", "Russia", "China", "United States"), 2, 1));
        questionList.add(new Question(195, "Địa lý", "Quốc gia nào nằm giữa Nga và Trung Quốc?", Arrays.asList("Mongolia", "Kazakhstan", "Ukraine", "Belarus"), 1, 1));
        questionList.add(new Question(196, "Địa lý", "Núi Kilimanjaro, đỉnh núi cao nhất châu Phi, nằm ở đất nước nào?", Arrays.asList("Kenya", "Tanzania", "South Africa", "Ethiopia"), 2, 1));
        questionList.add(new Question(197, "Địa lý", "Thành phố nào được biết đến với tên 'Thành phố cổ' ở Jordan?", Arrays.asList("Amman", "Aqaba", "Jerash", "Petra"), 4, 1));
        questionList.add(new Question(198, "Địa lý", "Dãy núi nào tạo thành biên giới tự nhiên giữa Ấn Độ và Nepal?", Arrays.asList("Dãy Himalaya", "Dãy Karakoram", "Dãy Hindu Kush", "Dãy Pamir"), 1, 1));
        questionList.add(new Question(199, "Địa lý", "Dòng sông nào là 'đứa con' của sông Mississippi ở Hoa Kỳ?", Arrays.asList("Missouri River", "Ohio River", "Arkansas River", "Columbia River"), 1, 1));
        questionList.add(new Question(200, "Địa lý", "Thành phố nào là thủ đô của quốc gia Brazil?", Arrays.asList("Rio de Janeiro", "Sao Paulo", "Brasília", "Salvador"), 3, 1));
        questionList.add(new Question(201, "Công nghệ", "Điện thoại di động đầu tiên trên thế giới được sản xuất vào năm nào?", Arrays.asList("1973", "1980", "1990", "2000"), 1, 0));
        questionList.add(new Question(202, "Công nghệ", "Từ viết tắt 'URL' trong công nghệ Internet là gì?", Arrays.asList("Universal Resource Locator", "Unified Remote Logging", "User Recognition Language", "Ultimate Resource Listing"), 1, 0));
        questionList.add(new Question(203, "Công nghệ", "Hệ điều hành 'Linux' được phát triển bởi ai?", Arrays.asList("Bill Gates", "Linus Torvalds", "Steve Jobs", "Mark Zuckerberg"), 2, 0));
        questionList.add(new Question(204, "Công nghệ", "Loại ổ đĩa 'SSD' viết tắt của từ gì trong công nghệ lưu trữ?", Arrays.asList("Super Storage Drive", "Solid State Drive", "System and Software Drive", "Sequential Storage Device"), 2, 0));
        questionList.add(new Question(205, "Công nghệ", "Khái niệm 'Cloud Computing' liên quan đến điều gì?", Arrays.asList("Lưu trữ dữ liệu trên đám mây", "Sử dụng máy tính không kết nối Internet", "Bảo mật mạng máy tính", "Phát triển phần mềm mà không cần Internet"), 1, 0));
        questionList.add(new Question(206, "Công nghệ", "Từ viết tắt 'AI' trong công nghệ máy học là gì?", Arrays.asList("Artificial Intelligence", "Automated Interface", "Advanced Internet", "Analog Interface"), 1, 0));
        questionList.add(new Question(207, "Công nghệ", "Ngôn ngữ lập trình 'Python' được đặt tên theo cái gì?", Arrays.asList("Rắn con", "Quốc gia Phần Lan", "Nhóm hài kịch Monty Python", "Một loại bánh pizza"), 3, 0));
        questionList.add(new Question(208, "Công nghệ", "Ứng dụng 'WhatsApp' thuộc sở hữu của công ty nào?", Arrays.asList("Google", "Facebook", "Microsoft", "Apple"), 2, 0));
        questionList.add(new Question(209, "Công nghệ", "Khái niệm 'Blockchain' phổ biến trong lĩnh vực nào?", Arrays.asList("Năng lượng tái tạo", "Tài chính và tiền tệ", "Nghiên cứu y học", "Thể thao điện tử"), 2, 0));
        questionList.add(new Question(210, "Công nghệ", "Nền tảng 'Android' được phát triển bởi công ty nào?", Arrays.asList("Apple", "Microsoft", "Google", "Samsung"), 3, 0));

        // Các câu hỏi chủ đề Công nghệ mức độ khó
        questionList.add(new Question(211, "Công nghệ", "Loại virus máy tính nào chủ yếu nhắm vào các tập tin hệ thống và phần mềm?", Arrays.asList("Trojan Horse", "Worm", "Spyware", "Adware"), 1, 1));
        questionList.add(new Question(212, "Công nghệ", "Khái niệm 'Big Data' liên quan đến điều gì?", Arrays.asList("Quy mô lớn của dữ liệu", "Máy tính cỡ lớn", "Mạng lưới Internet rộng lớn", "Bảng mạch điều khiển lớn"), 1, 1));
        questionList.add(new Question(213, "Công nghệ", "Nền tảng 'Blockchain' được sử dụng chủ yếu trong lĩnh vực nào?", Arrays.asList("Tài chính và tiền tệ", "Y học", "Du lịch", "Năng lượng tái tạo"), 1, 1));
        questionList.add(new Question(214, "Công nghệ", "Khái niệm 'Internet of Things (IoT)' ám chỉ điều gì?", Arrays.asList("Mạng lưới Internet mở rộng", "Máy tính đám mây", "Internet cho các tổ chức", "Kết nối các đối tượng thông qua Internet"), 4, 1));
        questionList.add(new Question(215, "Công nghệ", "Ngôn ngữ lập trình nào được sử dụng chủ yếu trong phát triển ứng dụng di động cho iOS?", Arrays.asList("Java", "Swift", "Python", "C++"), 2, 1));
        questionList.add(new Question(216, "Công nghệ", "Trong mạng máy tính, giao thức 'FTP' viết tắt của từ gì?", Arrays.asList("File Transfer Protocol", "File Text Protocol", "Free Transfer Platform", "Fast Transmission Process"), 1, 1));
        questionList.add(new Question(217, "Công nghệ", "Công nghệ 'Bluetooth' được đặt tên theo ai?", Arrays.asList("Harald Bluetooth", "Steve Bluetooth", "Mark Bluetooth", "Bluetooth Corporation"), 1, 1));
        questionList.add(new Question(218, "Công nghệ", "Khái niệm 'Augmented Reality (AR)' liên quan đến điều gì?", Arrays.asList("Thực tế ảo", "Thực tế ảo mở rộng", "Thực tế ảo tăng cường", "Thực tế ảo giả mạo"), 3, 1));
        questionList.add(new Question(219, "Công nghệ", "Từ viết tắt 'VPN' trong mạng máy tính là gì?", Arrays.asList("Virtual Private Network", "Very Personal Network", "Virtual Public Network", "Visual Processing Node"), 1, 1));
        questionList.add(new Question(220, "Công nghệ", "Công nghệ 'QR code' được phát triển tại quốc gia nào?", Arrays.asList("Japan", "China", "United States", "South Korea"), 1, 1));
        questionList.add(new Question(221, "Thiên văn học", "Sao Mộc nằm ở hệ mặt trời ở vị trí thứ mấy?", Arrays.asList("Thứ nhất", "Thứ hai", "Thứ ba", "Thứ tư"), 1, 0));
        questionList.add(new Question(222, "Thiên văn học", "Hệ mặt trời có bao nhiêu hành tinh?", Arrays.asList("6", "7", "8", "9"), 3, 0));
        questionList.add(new Question(223, "Thiên văn học", "Tên gọi khác của Sao Kim là gì?", Arrays.asList("Sao Thổ", "Sao Hỏa", "Sao Kim Quyền", "Sao Kim Mộc"), 3, 0));
        questionList.add(new Question(224, "Thiên văn học", "Cái gì tạo nên hệ mặt trời?", Arrays.asList("Mặt trời", "Sao Hỏa", "Hành tinh", "Sao Kim"), 1, 0));
        questionList.add(new Question(225, "Thiên văn học", "Loại hành tinh nào có vòng tròn quay xung quanh mình?", Arrays.asList("Sao Thổ", "Sao Kim", "Sao Hỏa", "Sao Mộc"), 4, 0));
        questionList.add(new Question(226, "Thiên văn học", "Câu hỏi của Albert Einstein về không gian và thời gian được giải quyết bởi nguyên tắc nào?", Arrays.asList("Nguyên tắc bất biến ánh sáng", "Nguyên tắc hấp dẫn của Newton", "Nguyên tắc bảo toàn năng lượng", "Nguyên tắc bảo toàn moment góc"), 1, 0));
        questionList.add(new Question(227, "Thiên văn học", "Ngày và đêm trên Trái Đất xảy ra do hiện tượng gì?", Arrays.asList("Quay quanh trục", "Chuyển động quanh Mặt Trời", "Hiện tượng núi lửa", "Ảnh hưởng của mặt trăng"), 2, 0));
        questionList.add(new Question(228, "Thiên văn học", "Cơ sở nào đặt ra giả thuyết về sự tồn tại của các đối tượng siêu khối đen trong vũ trụ?", Arrays.asList("Einstein", "Newton", "Galileo", "Hubble"), 1, 0));
        questionList.add(new Question(229, "Thiên văn học", "Sao chổi được tạo ra khi nào?", Arrays.asList("Khi một hành tinh bị phá hủy", "Khi các thiên thạch va chạm", "Khi một sao sáng chết", "Khi các sao băng gặp khí quyển Trái Đất"), 2, 0));
        questionList.add(new Question(230, "Thiên văn học", "Quốc gia nào đã phóng tên lửa mang con người lên vũ trụ đầu tiên?", Arrays.asList("Mỹ", "Nga", "Trung Quốc", "Anh"), 2, 0));
        questionList.add(new Question(231, "Thiên văn học", "Hiện tượng 'ánh sáng phân tử' được gặp nhiều nhất trong môi trường nào của vũ trụ?", Arrays.asList("Quasar", "Dust clouds", "Black hole", "Galaxy"), 2, 1));
        questionList.add(new Question(232, "Thiên văn học", "Sự kiện 'Big Bang' diễn ra cách đây khoảng bao nhiêu tỷ năm?", Arrays.asList("13.8 tỷ năm", "10 tỷ năm", "5 tỷ năm", "20 tỷ năm"), 1, 1));
        questionList.add(new Question(233, "Thiên văn học", "Tầm nhìn của vũ trụ bao xa?", Arrays.asList("Vô hạn", "100 nghìn năm ánh sáng", "1 triệu năm ánh sáng", "10 triệu năm ánh sáng"), 1, 1));
        questionList.add(new Question(234, "Thiên văn học", "Sao nào nổi tiếng với việc 'nuốt chửng' một số hành tinh và sao khác?", Arrays.asList("Betelgeuse", "Andromeda", "Uranus", "Cygnus X-1"), 4, 1));
        questionList.add(new Question(235, "Thiên văn học", "Trong hệ mặt trời, hành tinh nào có thời gian quay quanh Mặt Trời ngắn nhất?", Arrays.asList("Sao Hỏa", "Sao Mộc", "Sao Kim", "Sao Thổ"), 2, 1));
        questionList.add(new Question(236, "Thiên văn học", "Khám phá nào đã chứng minh rằng vũ trụ đang mở rộng?", Arrays.asList("Voyager 1", "Hubble Space Telescope", "Kepler Telescope", "COBE"), 2, 1));
        questionList.add(new Question(237, "Thiên văn học", "Sao nào nặng nhất trong số những ngôi sao đã được phát hiện?", Arrays.asList("Sao Mộc", "Sao Hỏa", "Sao Kim", "Sao Sirius"), 4, 1));
        questionList.add(new Question(238, "Thiên văn học", "Khái niệm 'thời gian không gian cong' được mô tả bởi nguyên tắc nào?", Arrays.asList("Nguyên tắc bất biến ánh sáng", "Nguyên tắc hấp dẫn của Newton", "Nguyên tắc bảo toàn năng lượng", "Nguyên tắc tương đối của Einstein"), 4, 1));
        questionList.add(new Question(239, "Thiên văn học", "Ngôi sao nào là ngôi sao nóng nhất trong số những ngôi sao đã được biết đến?", Arrays.asList("Sao Hỏa", "Sao Kim", "Sao Mộc", "Sao Rigel"), 4, 1));
        questionList.add(new Question(240, "Thiên văn học", "Vật thể nào được coi là 'ranh giới cuối cùng của vũ trụ'?", Arrays.asList("Hố đen", "Vật thể không xác định", "Tia hồng ngoại", "Sóng radio vũ trụ"), 1, 1));

        return questionList;
    }
}
