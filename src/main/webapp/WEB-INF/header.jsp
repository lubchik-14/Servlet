<div class="header btns clearfix">
    <div class="btn-home lft">
        <a href="home">Home</a>
    </div>
    <div class="btn-logout rgt">
        <a href="logout" class="${empty sessionScope.userId ? 'disabled' : ''}">Logout</a>
    </div>
</div>