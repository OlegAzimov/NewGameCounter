getPagination('#table');
// getPagination('.table-class');
//getPagination('table');

/*					PAGINATION
- on change max rows select options fade out all rows gt option value mx = 5
- append pagination list as per numbers of rows / max rows option (20row/5= 4pages )
- each pagination li on click -> fade out all tr gt max rows * li num and (5*pagenum 2 = 10 rows)
- fade out all tr lt max rows * li num - max rows ((5*pagenum 2 = 10) - 5)
- fade in all tr between (maxRows*PageNum) and (maxRows*pageNum)- MaxRows
*/


function getPagination(table) {
    var lastPage = 1;
    document.getElementById('size').innerHTML = gamesSize;
    document.getElementById('mobileSize').innerHTML = gamesSize;
    $('#maxRows')
        .on('change', function (evt) {
            //$('.paginationprev').html('');						// reset pagination

            lastPage = 1;
            $('.pagination')
                .find('li')
                .slice(1, -1)
                .remove();
            var trnum = 0; // reset tr counter
            var maxRows = parseInt($(this).val()); // get Max Rows from select option
            document.getElementById('page').innerHTML = $(this).val();
            document.getElementById('mobilePage').innerHTML = $(this).val();
            if (maxRows === 5000 || gamesSize <= maxRows) {
                $('.pagination').hide();
                document.getElementById('page').innerHTML = "всех";
                document.getElementById('mobilePage').innerHTML = "всех";
            } else {
                $('.pagination').show();
            }

            var totalRows = $(table + ' tbody tr').length; // numbers of rows
            $(table + ' tr:gt(0)').each(function () {
                // each TR in  table and not the header
                trnum++; // Start Counter
                if (trnum > maxRows) {
                    // if tr number gt maxRows

                    $(this).hide(); // fade it out
                }
                if (trnum <= maxRows) {
                    $(this).show();
                } // else fade in Important in case if it ..
            }); //  was fade out to fade it in
            if (totalRows > maxRows) {
                // if tr total rows gt max rows option
                var pagenum = Math.ceil(totalRows / maxRows); // ceil total(rows/maxrows) to get ..
                //	numbers of pages
                for (var i = 1; i <= pagenum;) {
                    // for each page append pagination li
                    $('.pagination #prev')
                        .before(
                            // <li className="page-item active" data-page="1"><a className="page-link">1</a></li>
                            '<li class="page-item" data-page="' +
                            i +
                            '">\
                                              <a class="page-link">' +
                            i++ +
                            '</a>\
                                            </li>'
                        )
                        .show();
                } // end for i
            } // end if row count > max rows
            $('.pagination [data-page="1"]').addClass('active'); // add active class to the first li
            $('.pagination li').on('click', function (evt) {
                // on click each page
                evt.stopImmediatePropagation();
                evt.preventDefault();
                var pageNum = $(this).attr('data-page'); // get it's number

                var maxRows = parseInt($('#maxRows').val()); // get Max Rows from select option

                if (pageNum === 'prev') {
                    if (lastPage === 1) {
                        return;
                    }
                    pageNum = --lastPage;
                }
                if (pageNum === 'next') {
                    if (lastPage === $('.pagination li').length - 2) {
                        return;
                    }
                    pageNum = ++lastPage;
                }

                lastPage = pageNum;
                var trIndex = 0; // reset tr counter
                $('.pagination li').removeClass('active'); // remove active class from all li
                $('.pagination [data-page="' + lastPage + '"]').addClass('active'); // add active class to the clicked
                // $(this).addClass('active');					// add active class to the clicked
                limitPagging();
                $(table + ' tr:gt(0)').each(function () {
                    // each tr in table not the header
                    trIndex++; // tr index counter
                    // if tr index gt maxRows*pageNum or lt maxRows*pageNum-maxRows fade if out
                    if (
                        trIndex > maxRows * pageNum ||
                        trIndex <= maxRows * pageNum - maxRows
                    ) {
                        $(this).hide();
                    } else {
                        $(this).show();
                    } //else fade in
                }); // end of for each tr in table
            }); // end of on click pagination list
            limitPagging();
        })
        .val(10)
        .change();

    // end of on select change

    // END OF PAGINATION
}

function limitPagging() {
    // alert($('.pagination li').length)

    if ($('.pagination li').length > 7) {
        if ($('.pagination li.active').attr('data-page') <= 3) {
            $('.pagination li:gt(10)').hide();
            $('.pagination li:lt(10)').show();
            $('.pagination [data-page="next"]').show();
        }
        if ($('.pagination li.active').attr('data-page') > 3) {
            $('.pagination li:gt(0)').hide();
            $('.pagination [data-page="next"]').show();
            for (let i = (parseInt($('.pagination li.active').attr('data-page')) - 2); i <= (parseInt($('.pagination li.active').attr('data-page')) + 2); i++) {
                $('.pagination [data-page="' + i + '"]').show();

            }

        }
    }
}

$(function () {
    // Just to append id number for each row
    $('table tr:eq(0)').prepend('<th> ID </th>');

    var id = 0;

    $('table tr:gt(0)').each(function () {
        id++;
        $(this).prepend('<td>' + id + '</td>');
    });
});

function findData(values, searchEl) {
    let find = false
    values.some(value => value.includes(searchEl) ? find = true : '')
    return find
}

$(document).ready(function () {
    // Filter table rows based on searched searchEl
    $("#search").on("keyup", function () {
        var searchEl = $(this).val().toLowerCase();
        if (searchEl === "") {
            getPagination('#table');
        } else {
            $("table tbody tr").each(function () {
                $row = $(this);
                let divs = $row.children()[4]
                    .childNodes[3]
                    .childNodes[1]
                    .childNodes[3].getElementsByClassName("data")
                let values = []
                Array.prototype.filter.call(
                    divs,
                    (div) => values.push(div.innerHTML.toLowerCase())
                )
                let name = $row.find("td:nth-child(2)").text().toLowerCase();
                let data = $row.find("td:nth-child(3)").text().toLowerCase();
                let number = $row.find("td:nth-child(4)").text().toLowerCase();
                if (name.search(searchEl) < 0 && data.search(searchEl) < 0 && number.search(searchEl) < 0 && !findData(values, searchEl)) {
                    $row.hide();
                } else {
                    $row.show();
                }
            });
        }
    });
});

function openScorePopup(button) {
    button.parentNode.childNodes[3].style.display = "flex";
}

function closeScorePopup(button) {
    button.parentNode.parentNode.style.display = "none"
}

//  Developed By Yasser Mas
// yasser.mas2@gmail.com
