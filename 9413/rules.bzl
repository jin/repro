def _my_rule_impl(ctx):

    foo_outfile = ctx.actions.declare_file(ctx.label.name + "-foo.out")
    ctx.actions.run_shell(
        outputs = [foo_outfile],
        command = "touch %s" % foo_outfile.path,
    )

    bar_outfile = ctx.actions.declare_file(ctx.label.name + "-bar.out")
    ctx.actions.run_shell(
        outputs = [bar_outfile],
        command = "touch %s" % bar_outfile.path,
    )

    baz_outfile = ctx.actions.declare_file(ctx.label.name + "-baz.out")
    ctx.actions.run_shell(
        outputs = [baz_outfile],
        command = "touch %s && exit 1" % baz_outfile.path,
    )

    return [
        OutputGroupInfo(
            foo_outputs = depset([foo_outfile]),
            bar_outputs = depset([bar_outfile]),
            baz_outputs = depset([baz_outfile])
        ),
    ]

my_rule = rule(
    implementation = _my_rule_impl,
    attrs = {},
)
