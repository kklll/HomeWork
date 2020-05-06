var indexVM = new Vue({
    el: '#main',
    data: {
        file: null,
        form: {
            name: '',
            id: '',
            region: '',
            homework:null,
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: '',
            selected: "",
            xuehao: "",
        },
        subjectsList: ["Linux实用技术", "单片机"],
        subject: "",
        studentID: "",
        name: "",
        list:null,
    },
    methods: {
        getSubjectsList: function () {
            let self = this;
            reqwest({
                url: '/list'
                , method: 'post'
                , error: function (err) {
                    console.log("err!")
                }
                , success: function (data) {
                    self.list = data;
                    console.log(data);
                }
            })
        },
        changeImage: function () {
            let imgNum = 240;
            let imgIndex = 0;

            while (imgIndex === 0) {
                imgIndex = Math.ceil(Math.random() * imgNum)
            }

            document.body.background = "img/p (" + imgIndex + ").jpg"
        },
        onSubmit() {
            var that = this;
            let formData = new FormData()
            formData.append('name', this.form.name)
            formData.append('xuehao', this.form.xuehao)
            formData.append('homework', this.form.selected)
            formData.append('file', this.file)
            var request = new XMLHttpRequest();
            request.open("POST", "/send", true)
            request.send(formData);
            request.onload = function (event) {
                if (request.status === 200) {
                    that.$message.success(request.responseText);
                } else {
                    that.$message.warning(request.responseText);
                }
            }
        },
        getFile(event) {
            this.file = event.target.files[0];
        },
        submitUpload() {
            this.onSubmit();
        },
    },
    mounted() {
        this.getSubjectsList();
        this.timer = setInterval(this.changeImage, 20000);
    }
});
// var Ctor = Vue.extend(indexVM)
// new Ctor().$mount('#main')